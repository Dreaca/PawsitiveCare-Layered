package controller;

import bo.BOFactory;
import bo.custom.PetBo;
import bo.custom.RecordBo;
import dto.PetDto;
import dto.RecordDto;
import dto.Tm.RecordTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RecordsController {
    public Label lblPetname;
    public Label lblPetId;
    public Label lblPetBreed;
    public Label lblAge;
    public Label lblColor;
    public Label lblGender;
    public Label lblOwner;
    public TableView tblRec;
    public TableColumn recId;
    public TableColumn datecol;
    public TableColumn descCol;
    private String petId;

    PetBo pet = (PetBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.PET);
    RecordBo rec = (RecordBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.RECORD);

    public void takePetID(String petId) throws SQLException {
        this.petId = petId;
        setCellValueFactory();
        loadTable();
        loadData();
    }

    void loadData() throws SQLException {
        PetDto petData = null;
        try {
            petData = pet.searchPet(petId);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        lblPetId.setText(petData.getPetId());
        lblAge.setText(String.valueOf(petData.getAge()));
        lblColor.setText(petData.getColor());
        lblGender.setText(petData.getPetGender());
        lblOwner.setText(petData.getOwnerId());
        lblPetname.setText(petData.getPetName());
        lblPetBreed.setText(petData.getPetBreed());
    }

    void loadTable()  {
        ObservableList<RecordTm> oblist = FXCollections.observableArrayList();
        List<RecordDto> list = null;
        try {
            list = rec.getRecords(petId);
            for (RecordDto d : list) {
                oblist.add(
                        new RecordTm(
                                d.getPetId(),
                                d.getRecordId(),
                                d.getDate(),
                                d.getDescription()
                        )
                );
            }
            tblRec.setItems(oblist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setCellValueFactory(){
        recId.setCellValueFactory(new PropertyValueFactory<>("recordId"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    public void addNewRecordOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/dashBoards/pets/addNewRecord.fxml"));
        AnchorPane node = loader.load();
        AddNewRecordController controller = loader.getController();
        controller.setPetID(this.petId);
        Scene scene = new Scene(node);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
