package controller;

import bo.BOFactory;
import bo.ScheduleBo;
import dto.ScheduleDto;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AddNewScheduleItemController extends ScheduleFormController {
    public AnchorPane root;
    public DatePicker dpkSchedule;
    public TextField txtTime;

    public ComboBox cmbVet;
    public ComboBox cmbDuration;
    public ComboBox cmbHours;
    private String schedId;
//    ScheduleModel mod = new ScheduleModel();
    private ScheduleBo bo = (ScheduleBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.SCHEDULE);
    private String generateNextScheduleId() throws SQLException {
        return bo.getNextShedId();
    }


//    VetModel model = new VetModel();
    public void initialize() {
        try {
            setData();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnBackOnAction(ActionEvent event) {
        close();
    }

    public void btnSaveOnAction(MouseEvent mouseEvent) {
        LocalDate date = dpkSchedule.getValue();
        String time = txtTime.getText();
        String vetName = (String) cmbVet.getValue();
        String duration = (String) cmbDuration.getValue();
        var dto = new ScheduleDto(schedId,date,time,vetName,duration);
        try {
            boolean b = bo.saveScheduleItem(dto);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"Schedule Item Saved !!!!").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }
    void close(){
        Stage sta = (Stage) root.getScene().getWindow();
        sta.close();
    }
    public void setData() throws SQLException, ClassNotFoundException {
        cmbDuration.getItems().setAll("8 hours","3 Hours","5 Hours");
        cmbVet.getItems().addAll(FXCollections.observableArrayList(bo.getAllVetNames()));
        List<LocalTime> list = new ArrayList<>();
        LocalTime start = LocalTime.of(6,0);
        LocalTime end = LocalTime.of(21,0);
        while (start.isAfter(end)){
            list.add(start);
            start.plusMinutes(30);
        }
        cmbHours.getItems().addAll(FXCollections.observableArrayList(list));
        schedId = generateNextScheduleId();
    }
}
