package controller;

import bo.BOFactory;
import bo.custom.LoginBo;
import dto.LoginFormDto;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginFormController {
    public TextField txtUserNAme;
    public JFXButton btnLogin;
    public PasswordField txtPassword;
    public AnchorPane root;
    public AnchorPane stuff;
    public Label incorrect;
    public Hyperlink hyperForgotPass;
    public Label lblEnterNIC;
    public TextField txtNICDigit;
    private LoginBo bo = (LoginBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.LOGIN);
    public Image bgs = new Image("view/Assets/image/puppy.jpg");
    public void initialize(){
        incorrect.setVisible(false);
        root.setStyle("-fx-background-image : url("+
                bgs.getUrl()+");"+
                "-fx-background-size : cover;");

    }

    public void loginOnAction(ActionEvent actionEvent) {

        String userName = txtUserNAme.getText();
        String password = txtPassword.getText();
        if(checkValidity()) {
            LoginFormDto loginFormDto = new LoginFormDto(userName, password);

            boolean authenticateResult = false;
            try {
                authenticateResult = bo.authenticateUser(loginFormDto);


                if (authenticateResult) {
                    String currentUser = bo.getUserDetail(loginFormDto);
                    if (currentUser.startsWith("A")) {
                        loadAdminDash(userName);
                    } else if (currentUser.startsWith("U")) {
                        loadEmployeeDash(userName);
                    }
                } else {
                    txtUserNAme.clear();
                    txtPassword.clear();
                    incorrect.setVisible(true);
                    hyperForgotPass.setVisible(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadAdminDash(String userName) throws SQLException {
        AnchorPane anchorPane = null;
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("/view/dashBoards/AdminDash/adminDash.fxml"));
            anchorPane = loader.load();
            Scene scene = new Scene(anchorPane);
            AdminFormController controller = loader.getController();
            controller.setUSerName(userName);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setTitle("Admin Dashboard");
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void loadEmployeeDash(String userName){
        AnchorPane anchorPane = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashBoards/EmployeeDash/empDash.fxml"));
            anchorPane = loader.load();
            Scene scene = new Scene(anchorPane);
            EmployeeDashController controller = loader.getController();
            controller.setUserName(userName);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setTitle("Employee Dashboard");
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private boolean checkValidity() {
        if(Pattern.matches("^(\\s)",txtUserNAme.getText())){
            incorrect.setVisible(true);
            return false;
        }
        if(Pattern.matches("^(\\s)",txtPassword.getText())){
            incorrect.setVisible(true);
            return false;
        }
        else return true;
    }


    public void forgotPassOnAction(ActionEvent event) {
        lblEnterNIC.setVisible(true);
        txtNICDigit.setVisible(true);
    }


    public void checkNIConAction(ActionEvent event) {
        String nic = txtNICDigit.getText();
        String userId = null;
        try {
            userId = bo.checkValidity(nic);

        if(userId.equals(null)){
            new Alert(Alert.AlertType.ERROR,"Something went wrong. Please Contact Service Provider for assistance").show();
        }else {
            if(userId.startsWith("A")){
                loadAdminDash("Admin");
            } else if (userId.startsWith("E")) {
                loadEmployeeDash("Employee");
            }
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
