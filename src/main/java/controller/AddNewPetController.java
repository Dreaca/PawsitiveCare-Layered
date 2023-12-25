package controller;

import bo.BOFactory;
import bo.custom.PetBo;
import dto.PetDto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
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
    public void savePetOnAction()  {
        try {

                String petId = lblPetId.getText();
                String petName = txtPetName.getText();
                String petBreed = String.valueOf(cmbBreed.getValue());
                String gender = (String) cmbGender.getValue();
                String ownerId = null;

                    ownerId = bo.getCustomerId(txtOwner.getText());

                String color = txtColor.getText();

                var dto = new PetDto(petId,petName,petBreed,gender,ownerId,color);
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
            lblPetId.setText(bo.getNextPetId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadBreedAndGender();
    }
    private void loadBreedAndGender() {
        for (String string : Arrays.asList("Dog", "Cat", "Bird", "other")) {
            cmbBreed.getItems().add(string);
        }
        for(String s : Arrays.asList("Male","Female")){
            cmbGender.getItems().add(s);
        }
    }
}
