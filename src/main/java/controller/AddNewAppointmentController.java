package controller;

import bo.AddNewAppointmentBo;
import bo.BOFactory;
import dao.custom.AppointmentDao;
import dao.custom.Impl.AppointmentDAOImpl;
import dto.AppointmentDto;
import dto.CustomerDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AppointmentModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddNewAppointmentController {
    public AnchorPane root;
    public Label lblAppId;
    public DatePicker dpkDate;
    public Label lblPrice;
    public TextField txtTime;
    public TextField txtCustomer;
    public TextField txtCustomerContact;
    public ComboBox cmbApType;
    private AddNewAppointmentBo bo = (AddNewAppointmentBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.ADDAPPOINTMENT);

    public void cancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    public void initialize() throws SQLException {
        loadData();
        lblAppId.setText(bo.getNextAppid());
        cmbApType.setOnAction(event -> {
            lblPrice.setText(getPriceFor(AppointmentDto.AppType.valueOf(((AppointmentDto.AppType) cmbApType.getValue()).name())));
        });

    }

    public void loadData(){
            cmbApType.getItems().addAll(AppointmentDto.AppType.values());
    }
    public void AddAppointmentOnAction(ActionEvent actionEvent) {

        String appId = lblAppId.getText();
        String customer = txtCustomer.getText();
        AppointmentDto.AppType type = (AppointmentDto.AppType) cmbApType.getValue();
        lblPrice.getText();
        String time = txtTime.getText();
        String contact = txtCustomerContact.getText();
        String date = String.valueOf(dpkDate.getValue());

        if (checkValidity()) {
            var dto = new AppointmentDto(appId,customer,contact,type,time,date);

            try {
                boolean isSaved = bo.saveAppointment(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Appointment Saved").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private boolean checkValidity() {
        boolean matches = Pattern.matches("(App)[0-9]{1,}", lblAppId.getText());
        if(!matches){
            new Alert(Alert.AlertType.ERROR,"wrong AppId").show();
        }
        if(!Pattern.matches("[0-9][0-9](:)[0-9][0-9]",txtTime.getText())){
            new Alert(Alert.AlertType.ERROR,"Time format wrong").show();
        }
        if(!Pattern.matches("[A-za-z]\\s[A-za-z]",txtCustomer.getText())){
            new Alert(Alert.AlertType.ERROR,"Customer Name Wrong");
        }
        if(!Pattern.matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$`",txtCustomerContact.getText())){
            new Alert(Alert.AlertType.ERROR,"Customer Contact is wrong").show();
        }
         return true;
    }

    private String getPriceFor(AppointmentDto.AppType type) {
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


    public void AddCustomerOnACtion(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/dashBoards/common/customerForm.fxml"))));
        stage.centerOnScreen();
        stage.show();
    }
    public void loadAppointments(){

    }
}
