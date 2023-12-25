package controller;

import bo.BOFactory;
import bo.PetBo;
import dto.PetDto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CustomerModel;
import model.PetModel;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.Arrays;

public class AddNewPetController {
    public ComboBox cmbBreed;
    public ComboBox cmbGender;
    public TextField txtOwner;
    public Label lblPetId;
    public TextField txtColor;
    public TextField txtPetName;
    public AnchorPane recordsPane;
    public Hyperlink uploadLink;
//    PetModel model = new PetModel();
    private PetBo bo = (PetBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.PET);
    public void savePetOnAction() throws SQLException {
        String petId = lblPetId.getText();
        String petName = txtPetName.getText();
        String petBreed = String.valueOf(cmbBreed.getValue());
        String gender = (String) cmbGender.getValue();
        String ownerId = CustomerModel.getCustomerId(txtOwner.getText());
        String color = txtColor.getText();

        var dto = new PetDto(petId,petName,petBreed,gender,ownerId,color);
        boolean isSaved = false;
        try {
            isSaved = bo.savePet(dto);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION,"Pet Saved ").show();
//            PetFormController.loadAllData();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }

    }
    public void initialize()  {
        try {
            lblPetId.setText(bo.getNextPetId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadBreedAndGender();
    }

    public void addRecordOnAction() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashBoards/pets/addNewRecord.fxml"))));
        stage.show();
    }
    public void uploadOnAction(){

    }
    private void loadBreedAndGender() {
        for (String string : Arrays.asList("Dog", "Cat", "Bird", "other")) {
            cmbBreed.getItems().add(string);
        }
        for(String s : Arrays.asList("Male","Female")){
            cmbGender.getItems().add(s);
        }
    }

    public void dropOnAction(DragEvent dragEvent) {

    }
}
