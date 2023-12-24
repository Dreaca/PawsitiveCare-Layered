package controller;

import Dto.PetDto;
import Dto.Tm.PetTm;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CustomerModel;
import model.PetModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PetFormController {
    public ComboBox cbmFilter;
    public TableColumn colName;
    public TableColumn colId;
    public TableColumn colBreed;
    public TableColumn colGender;
    public TableColumn colOwner;
    public TableColumn colRec;
    public TableView tblPet;
    public TableColumn colColor;
    public TableColumn colModify;
    private Image im = new Image("/view/Assets/icon/settings.png");
    private ImageView imv = new ImageView(im);

    public PetFormController() throws IOException {
    }

    public void combofiller(){
        for (String string : Arrays.asList("Dog", "Cat", "Birds", "Other")) {
            cbmFilter.getItems().add(string);
        }
    }

    public void initialize(){
        combofiller();
        setCellValueFactory();
        loadAllData();

    }

    void loadAllData() {
        PetModel model = new PetModel();
        ObservableList<PetTm> oblist = FXCollections.observableArrayList();
        try {
            List<PetDto> list = model.getAllPets();
            for (PetDto pet: list) {
                oblist.add(
                  new PetTm(
                          pet.getPetId(),
                          pet.getPetName(),
                          pet.getPetBreed(),
                          pet.getPetGender(),
                          pet.getColor(),
                          CustomerModel.getCustomerName(pet.getOwnerId()),
                          null,
                          new JFXButton("Modify")
                  )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < oblist.size(); i++) {
            int finalI = i;
            int finalI1 = i;
            int finalI2 = i;
            int finalI3 = i;
            int finalI4 = i;
            oblist.get(i).getModifyButton().setOnAction(actionEvent -> {
                try {
                    JFXButton bt = oblist.get(finalI1).getModifyButton();
                    imv.setFitWidth(20);
                    imv.setFitHeight(20);
                    bt.setGraphic(imv);
                    double x = bt.localToScreen(bt.getBoundsInLocal()).getMinX();
                    double y = bt.localToScreen(bt.getBoundsInLocal()).getMinY();
                   ContextMenu con =  loadPopup(oblist.get(finalI).getModifyButton());
                   con.getItems().get(0).setOnAction(actionEvent1 -> {
                       System.out.println( oblist.get(finalI2).getPetId()+"ACtion event eke eka");
                       loadUpdate(oblist.get(finalI2).getPetId());
                       loadAllData();
                       tblPet.refresh();
                   });
                   con.getItems().get(1).setOnAction(actionEvent1 -> {
                       try {
                           if (model.deletePet(oblist.get(finalI3).getPetId())) {
                               new Alert(Alert.AlertType.CONFIRMATION,"Pet Deleted !!!").show();
                               loadAllData();
                           }
                       } catch (SQLException e) {
                           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                       }
                       tblPet.refresh();
                   });
                   con.show(bt,x,y);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        tblPet.setItems(oblist);
        tblPet.refresh();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("petId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBreed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colOwner.setCellValueFactory(new PropertyValueFactory<>("owner"));
        colRec.setCellValueFactory(new PropertyValueFactory<>("records"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colModify.setCellValueFactory(new PropertyValueFactory<>("modifyButton"));
    }

    public void addNewPetOnaction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/dashBoards/pets/addNewPet.fxml"))));
        stage.centerOnScreen();
        stage.show();
    }

    public void refreshOnAction(){
        loadAllData();
        tblPet.refresh();
    }
    public ContextMenu loadPopup(JFXButton modifyButton) throws IOException {
        ContextMenu con = new ContextMenu();
        MenuItem button1 = new MenuItem("Update");
        MenuItem button2 = new MenuItem("Delete");
        con.getItems().addAll(button1, button2);

        button2.setOnAction(actionEvent -> {
            System.out.println("delete button clicked");
        });
        return con;
    }
    
    private void loadUpdate(String petId) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashBoards/pets/updatePet.fxml"));
            Parent root = loader.load();
            UpdatePetController controller = loader.getController();
            controller.setPetId(petId);
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

}
