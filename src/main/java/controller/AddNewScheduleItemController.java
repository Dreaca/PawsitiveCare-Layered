package controller;

import bo.BOFactory;
import bo.custom.ScheduleBo;
import dto.ScheduleDto;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
    public ComboBox cmbVet;
    public ComboBox cmbDuration;
    public ComboBox cmbHours;
    private String schedId;
    private ScheduleBo bo = (ScheduleBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.SCHEDULE);
    private String generateNextScheduleId() throws SQLException {
        return bo.getNextShedId();
    }


//    VetModel model = new VetModel();
    public void initialize() {
        setData();
    }

    public void btnBackOnAction(ActionEvent event) {
        close();
    }

    public void btnSaveOnAction(ActionEvent mouseEvent) {
        LocalDate date = dpkSchedule.getValue();
        LocalTime time = (LocalTime) cmbHours.getValue();
        String vetName = (String) cmbVet.getValue();
        String duration = (String) cmbDuration.getValue();
        var dto = new ScheduleDto(schedId,date,time,vetName,duration);
        try {
            boolean b = bo.saveScheduleItem(dto);
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"Schedule Item Saved !!!!").show();
                close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }
    void close(){
        Stage sta = (Stage) root.getScene().getWindow();
        sta.close();
    }
    public void setData(){
        cmbDuration.getItems().setAll("8 hours","3 Hours","5 Hours");
        try {
            cmbVet.getItems().addAll(FXCollections.observableArrayList(bo.getAllVetNames()));
            schedId = generateNextScheduleId();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<LocalTime> list = new ArrayList<>();
        list.add(LocalTime.of(6,0));
        list.add(LocalTime.of(7,0));
        list.add(LocalTime.of(8,0));
        list.add(LocalTime.of(9,0));
        list.add(LocalTime.of(10,0));
        list.add(LocalTime.of(11,0));
        list.add(LocalTime.of(12,0));
        list.add(LocalTime.of(13,0));
        list.add(LocalTime.of(14,0));
        list.add(LocalTime.of(15,0));
        list.add(LocalTime.of(16,0));
        list.add(LocalTime.of(17,0));
        list.add(LocalTime.of(18,0));
        list.add(LocalTime.of(19,0));
        list.add(LocalTime.of(20,0));
        list.add(LocalTime.of(21,0));
        cmbHours.getItems().addAll(FXCollections.observableArrayList(list));

    }
}
