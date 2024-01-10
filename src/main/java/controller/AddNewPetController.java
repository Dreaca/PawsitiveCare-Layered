package controller;

import bo.BOFactory;
import bo.custom.PetBo;
import dto.PetDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class AddNewPetController {
    public ComboBox cmbBreed;
    public ComboBox cmbGender;
    public ComboBox txtOwner;
    public Label lblPetId;
    public TextField txtColor;
    public TextField txtPetName;
    public TextField txtPetAge;
    public AnchorPane root;
    private PetBo bo = (PetBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.PET);
    public void savePetOnAction()  {
        try {

                String petId = lblPetId.getText();
                String petName = txtPetName.getText();
                String petBreed = String.valueOf(cmbBreed.getValue());
                String gender = (String) cmbGender.getValue();
                String ownerId = null;
                int age = Integer.parseInt(txtPetAge.getText());

                    ownerId = bo.getCustomerId((String) txtOwner.getValue());

                String color = txtColor.getText();

                var dto = new PetDto(petId,petName,age,petBreed,gender,color,ownerId);
                boolean
                    isSaved = bo.savePet(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Pet Saved ").show();
        //            PetFormController.loadAllData();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
                }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void initialize()  {
        try {
            String id = bo.getNextPetId();
            System.out.println(id);
            lblPetId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadBreedAndGender();
    }
    private void loadBreedAndGender() {
        try {
            txtOwner.getItems().addAll(bo.getAllCustId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (String string : Arrays.asList("Dog", "Cat", "Bird", "other")) {
            cmbBreed.getItems().add(string);
        }
        for(String s : Arrays.asList("Male","Female")){
            cmbGender.getItems().add(s);
        }
    }

    public void cancelPetOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
}
