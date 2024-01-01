package controller;

import bo.BOFactory;
import bo.custom.RecordBo;
import dto.RecordDto;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;

public class AddNewRecordController{
    @FXML
    private DatePicker dpkDate;
    @FXML
    private AnchorPane root;
    public Label recId;
    public Label petId;
    public TextArea txtDescription;
    private RecordBo bo = (RecordBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.RECORD);
    public void initialize() throws SQLException {
        recId.setText(bo.getNextRecordId());
    }
    public void backOnAction(){
        Stage window = (Stage) root.getScene().getWindow();
        window.close();
    }
    public void addRecordOnAction(){
        String petID = petId.getText();
        String recId = this.recId.getText();
        LocalDate date = (dpkDate.getValue());
        String desc = txtDescription.getText();

        RecordDto dto = new RecordDto(petID,recId,date,desc);
        try {
            if(bo.saveRecord(dto)){
                new Alert(Alert.AlertType.CONFIRMATION,"Record Saved").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPetID(String petId) {
        this.petId.setText(petId);
    }
}
