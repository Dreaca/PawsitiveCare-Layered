package controller;

import Dto.AppointmentDto;
import Dto.AppointmentDto.AppType;
import Dto.Tm.AppointmentTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.AppointmentModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AppointmentsFormController {
    public TableView tblAppointment;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colCustomer;
    public TableColumn colContact;
    public TableColumn colType;
    public TableColumn colPrice;

    public void initialize(){
        setCellValueFactory();
        loadAllAppointments();
    }

    private void loadAllAppointments() {
        var model = new AppointmentModel();
        ObservableList<AppointmentTm> oblist = FXCollections.observableArrayList();
        try {
            List<AppointmentDto> list = model.getAllAppointments();
            for (AppointmentDto d: list) {
                oblist.add(
                        new AppointmentTm(
                                d.getDate(),
                                d.getTime(),
                                d.getCustomerName(),
                                d.getContact(),
                                d.getType(),
                                99999.99
                        )
                );
            }
            tblAppointment.setItems(oblist);
            tblAppointment.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNewAppointmentONAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene((FXMLLoader.load(getClass().getResource("/view/dashBoards/EmployeeDash/addNewAppointment.fxml")))));
        stage.show();
    }
    public void setCellValueFactory(){
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void clearTable(ActionEvent actionEvent) {
        ObservableList<AppointmentTm> oblist = FXCollections.observableArrayList();
        oblist.add(null);
        tblAppointment.setItems(oblist);
        tblAppointment.refresh();
    }
}
