package controller;

import bo.BOFactory;
import bo.custom.AppointmentBo;
import dto.AppointmentDto;
import dto.AppointmentDto.AppType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import static dto.AppointmentDto.AppType.values;

public class UpdateAppointmentController {

    @FXML
    private ComboBox<AppType> cmbApType;

    @FXML
    private DatePicker dpkDate;

    @FXML
    private Label lblAppId;

    @FXML
    private Label lblPrice;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtCustomer;

    @FXML
    private TextField txtCustomerContact;

    @FXML
    private TextField txtTime;

    AppointmentBo bo = (AppointmentBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.APPOINTMENT);

    @FXML
    void AddAppointmentOnAction(ActionEvent event) {
        String appId = lblAppId.getText();
        String customer = txtCustomer.getText();
        AppointmentDto.AppType type = (AppointmentDto.AppType) cmbApType.getValue();
        Double price = Double.valueOf(lblPrice.getText());
        String time = txtTime.getText();
        String contact = txtCustomerContact.getText();
        String date = String.valueOf(dpkDate.getValue());

        if (checkValidity()) {
            var dto = new AppointmentDto(appId,customer,contact,type,time,date,price);

            try {
                boolean isSaved = bo.updateAppointment(dto);
                if (isSaved) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment Saved",ButtonType.OK);
                    alert.showAndWait();
                    ButtonType ok = alert.getButtonTypes().get(0);
                    alert.setOnCloseRequest(dialogEvent -> {
                        if (ok.equals(alert.getResult())){
                            cancelOnAction(event);
                        }
                    });
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Customer Not In System!. Would You like to add ?", ButtonType.OK, ButtonType.NO);
                    ButtonType okButton = alert.getButtonTypes().get(0);
                    ButtonType noButton = alert.getButtonTypes().get(1);
                    alert.setOnCloseRequest(dialogEvent -> {
                        if (okButton.equals(alert.getResult())) AddCustomerOnACtion(event);
                        else if (noButton.equals(alert.getResult()))cancelOnAction(event);
                    });
                    alert.show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    void AddCustomerOnACtion(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/dashBoards/common/customerForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.centerOnScreen();
        stage.show();
    }
    private boolean checkValidity() {
        boolean matches = Pattern.matches("(APP)[0-9]{1,}", lblAppId.getText());
        if(!matches){
            new Alert(Alert.AlertType.ERROR,"wrong AppId").show();
        }
        if(!Pattern.matches("[0-9][0-9](:)[0-9][0-9]",txtTime.getText())){
            new Alert(Alert.AlertType.ERROR,"Time format wrong").show();
        }
        if(!Pattern.matches("[A-za-z]\\s[A-za-z]",txtCustomer.getText())){
            new Alert(Alert.AlertType.ERROR,"Customer Name Wrong");
        }
        if(Pattern.matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$`",txtCustomerContact.getText())){
            new Alert(Alert.AlertType.ERROR,"Customer Contact is wrong").show();
        }
        return true;
    }
    @FXML
    void cancelOnAction(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    void searchCustomerOnAction(ActionEvent event) {

    }

    public void initialize(){
        loadData();
        try {
            lblAppId.setText(bo.getNextAppid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cmbApType.setOnAction(event -> {
            lblPrice.setText(getPriceFor(AppType.valueOf(((AppType) cmbApType.getValue()).name())));
        });
    }
    public void loadData(){
        cmbApType.getItems().addAll(values());
    }
    public void setAppId(String appId) {
        try {
            AppointmentDto appointment = bo.getAppointment(appId);
            lblAppId.setText(appId);
            dpkDate.setPromptText(appointment.getDate());
            cmbApType.setPromptText(String.valueOf((appointment.getType())));
            lblPrice.setText(String.valueOf(appointment.getPrice()));
            txtCustomer.setText(appointment.getCustomerName());
            txtCustomerContact.setText(appointment.getContact());
            txtTime.setText(appointment.getTime());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private String getPriceFor(AppType type) {
        String price = " ";
        switch (type) {
            case CHECKUP:
                price = "500";
                return price;
            case SURGERY:
                price =  "1500";
                return price;
            case VACCINATION:
                price =  "1000";
                return price;
        }
        return "Not applicaple";
    }
}
