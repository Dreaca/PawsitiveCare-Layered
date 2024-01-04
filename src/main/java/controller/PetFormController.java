package controller;

import bo.BOFactory;
import bo.custom.PetBo;
import dto.PetDto;
import dto.Tm.PetTm;
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

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class PetFormController {
    public TableColumn colName;
    public TableColumn colId;
    public TableColumn colBreed;
    public TableColumn colGender;
    public TableColumn colOwner;
    public TableColumn colRec;
    public TableView tblPet;
    public TableColumn colColor;
    public TableColumn colModify;
    public TableColumn colAge;
    public Label petCount;
    public Label date;
    public Label time;
    private Image im = new Image("/view/Assets/icon/settings.png");
    private ImageView imv = new ImageView(im);

    private PetBo bo = (PetBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.PET);

    public void initialize(){
        setCellValueFactory();
        loadAllData();

    }

    void loadAllData() {
//        PetModel model = new PetModel();
        ObservableList<PetTm> oblist = FXCollections.observableArrayList();
        try {
            List<PetDto> list = bo.getAllPets();
            for (PetDto pet: list) {
                oblist.add(
                  new PetTm(
                          pet.getPetId(),
                          pet.getPetName(),
                          pet.getAge(),
                          pet.getPetBreed(),
                          pet.getPetGender(),
                          pet.getColor(),
                          bo.getCustomerName(pet.getOwnerId()),
                          getnewJfxBtn(),
                          getnewJfxBtn()
                  )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < oblist.size(); i++) {
            int finalI = i;
            int finalI1 = i;
            int finalI2 = i;
            int finalI3 = i;
            oblist.get(i).getModifyButton().setOnAction(actionEvent -> {
                try {
                    JFXButton bt = oblist.get(finalI1).getModifyButton();
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
                            if (bo.deletePet(oblist.get(finalI3).getPetId())) {
                                new Alert(Alert.AlertType.CONFIRMATION,"Pet Deleted !!!").show();
                                loadAllData();
                            }
                        } catch (SQLException e) {
                            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        tblPet.refresh();
                    });
                    con.show(bt,x,y);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            int finalI4 = i;
            oblist.get(i).getRecords().setOnAction(event -> {
                try {
                    loadRecordsPane(oblist.get(finalI4).getPetId());
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
        colAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
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
    private void loadRecordsPane(String petId) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashBoards/pets/records.fxml"));
        AnchorPane node = fxmlLoader.load();
        RecordsController controller = fxmlLoader.getController();
        try {
            controller.takePetID(petId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(node);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    private JFXButton getnewJfxBtn() {
        Image im = new Image("/view/Assets/icon/settings.png");
        ImageView imv = new ImageView(im);
        imv.setFitWidth(20);
        imv.setFitHeight(20);
        JFXButton bt = new JFXButton();
        bt.setGraphic(imv);
        return bt;
    }

}
