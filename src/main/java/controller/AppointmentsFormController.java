package controller;

import bo.custom.AppointmentBo;
import bo.BOFactory;
import com.jfoenix.controls.JFXButton;
import dto.AppointmentDto;
import dto.Tm.AppointmentTm;
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
import java.util.List;

public class AppointmentsFormController {
    public TableView tblAppointment;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colCustomer;
    public TableColumn colContact;
    public TableColumn colType;
    public TableColumn colPrice;
    public AnchorPane stuff;
    public Label appointmentCount;
    public Label vaccineCount;
    public Label surgeryCount;
    public Label checkupCount;
    public TableColumn colMod;

    private AppointmentBo bo = (AppointmentBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.APPOINTMENT);
    public void initialize(){
        loadCounters();
        setCellValueFactory();
        loadAllAppointments();
    }

    private void loadAllAppointments() {
        ObservableList<AppointmentTm> oblist = FXCollections.observableArrayList();
        try {
            List<AppointmentDto> list = bo.getAllAppointments();
            for (AppointmentDto d: list) {
                oblist.add(
                        new AppointmentTm(
                                d.getAppId(),
                                d.getDate(),
                                d.getTime(),
                                d.getCustomerName(),
                                d.getContact(),
                                d.getType(),
                                d.getPrice(),
                                getAButton()
                        )
                );
            }

            for (int i = 0; i < oblist.size(); i++) {
                int finalI = i;
                int finalI1 = i;
                int finalI2 = i;
                int finalI3 = i;
                oblist.get(i).getMod().setOnAction(actionEvent -> {
                    try {
                        JFXButton bt = oblist.get(finalI1).getMod();
                        double x = bt.localToScreen(bt.getBoundsInLocal()).getMinX();
                        double y = bt.localToScreen(bt.getBoundsInLocal()).getMinY();
                        ContextMenu con = loadPopup(oblist.get(finalI).getMod());
                        con.getItems().get(0).setOnAction(actionEvent1 -> {
                            loadUpdate(oblist.get(finalI3).getAppId());
                        });
                        con.getItems().get(1).setOnAction(actionEvent1 -> {
                            deleteAppointment(oblist.get(finalI2).getAppId());
                        });
                        con.show(bt,x,y);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            tblAppointment.setItems(oblist);
            tblAppointment.refresh();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteAppointment(String appId) {
        try {
            if (bo.deleteAppointment(appId)){
                loadAllAppointments();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private JFXButton getAButton() {
        Image im = new Image("/view/Assets/icon/settings.png");
        ImageView imv = new ImageView(im);
        imv.setFitWidth(20);
        imv.setFitHeight(20);
        JFXButton bt = new JFXButton();
        bt.setGraphic(imv);
        return bt;
    }
    public ContextMenu loadPopup(JFXButton modifyButton) throws IOException {
        ContextMenu con = new ContextMenu();
        MenuItem button1 = new MenuItem("Update");
        MenuItem button2 = new MenuItem("Delete");
        con.getItems().addAll(button1, button2);
        return con;
    }
    private void loadUpdate(String appId) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashBoards/EmployeeDash/updateAppointment.fxml"));
            Parent root = loader.load();
            UpdateAppointmentController controller = loader.getController();
            controller.setAppId(appId);
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
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
        colMod.setCellValueFactory(new PropertyValueFactory<>("mod"));
    }

    public void clearTable(ActionEvent actionEvent) {
        ObservableList<AppointmentTm> oblist = FXCollections.observableArrayList();
        oblist.add(null);
        tblAppointment.setItems(oblist);
        tblAppointment.refresh();
    }
    private void loadCounters(){
        try {
            checkupCount.setText(bo.count(AppointmentDto.AppType.CHECKUP));
            vaccineCount.setText(bo.count(AppointmentDto.AppType.VACCINATION));
            surgeryCount.setText(bo.count(AppointmentDto.AppType.SURGERY));
            appointmentCount.setText(bo.countAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
