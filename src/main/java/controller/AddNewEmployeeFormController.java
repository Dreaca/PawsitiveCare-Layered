package controller;

import Dto.EmployeeDto;
import Dto.LoginFormDto;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.EmployeeModel;
import model.LoginModel;

import java.sql.SQLException;
import java.util.Objects;

public class AddNewEmployeeFormController {

    public Label lbluserID;
    public TextField txtEmpNIC;
    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpload;

    @FXML
    private CheckBox chkAdmin;

    @FXML
    private ImageView empImg;

    @FXML
    private Label lblEmployeeID;

    @FXML
    private TextField txtConfirmPass;

    @FXML
    private TextField txtEmpAddress;

    @FXML
    private TextField txtEmpContact;

    @FXML
    private TextField txtEmpFirstNAme;

    @FXML
    private TextField txtEmpLastName;

    @FXML
    private TextField txtEmpSalary;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;
    private Stage stage;
    public static boolean savedEmployee = false;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    private EmployeeModel employeeModel = new EmployeeModel();
    private LoginModel logModel = new LoginModel();
    public void initialize() throws SQLException {
        lblEmployeeID.setText(EmployeeModel.generateNextEmpId());
        lbluserID.setText(LoginModel.generateNExtUserID());
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        ManageEmployeeController.stage.close();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = lblEmployeeID.getText();
        String name = txtEmpFirstNAme.getText()+" "+txtEmpLastName.getText();
        String address = txtEmpAddress.getText();
        String contact = txtEmpContact.getText();
        Double salary = Double.valueOf(txtEmpSalary.getText());
        String userId = lbluserID.getText();
        String userName = txtUserName.getText();
        String newPw = txtPassword.getText();
        String confPw = txtConfirmPass.getText();
        String NIC = txtEmpNIC.getText();


        var dto = new EmployeeDto(id,name,address,contact,salary,userId,NIC);

        try {
            boolean con = confirmPass(newPw, confPw);
            if(con){
                var LDto = new LoginFormDto(userId,userName,newPw);
                boolean userSaved = logModel.saveUser(LDto);

                if(userSaved) {
                    boolean isSaved = employeeModel.saveEmployee(dto);

                    if (isSaved) {
                        savedEmployee = isSaved;
                        new Alert(Alert.AlertType.CONFIRMATION, "User Saved !").show();
                    }
                }
            }
        } catch (SQLException e) {
             new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private boolean confirmPass(String newPw, String confPw) {
        return Objects.equals(newPw, confPw);
    }

    @FXML
    void btnUploadOnAction(ActionEvent event) {


    }

    @FXML
    void onCheckPassWord(KeyEvent event) {
        String newPassword = txtPassword.getText();
        String confirmNewPassword = txtConfirmPass.getText();

        if (newPassword.equals(confirmNewPassword)) {
            //passwordMatchLabel.setText("Passwords match");
            txtConfirmPass.setStyle("-fx-text-fill: green;");
        } else {
            //passwordMatchLabel.setText("Passwords do not match");
            txtConfirmPass.setStyle("-fx-text-fill: red;");
        }
    }

}