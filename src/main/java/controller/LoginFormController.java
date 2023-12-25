package controller;

import bo.BOFactory;
import bo.custom.LoginBo;
import dto.LoginFormDto;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public TextField txtUserNAme;
    public JFXButton btnLogin;
    public PasswordField txtPassword;
    public AnchorPane root;
    public AnchorPane stuff;
    public Label incorrect;
    private LoginBo bo = (LoginBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.LOGIN);

    public void initialize(){
        incorrect.setVisible(false);
    }

    public void loginOnAction(ActionEvent actionEvent) {

        String userName = txtUserNAme.getText();
        String password = txtPassword.getText();

        LoginFormDto loginFormDto = new LoginFormDto(userName,password);

        boolean authenticateResult = false;
        try {
            authenticateResult = bo.authenticateUser(loginFormDto);


        if(authenticateResult){
            String currentUser = bo.getUserDetail(loginFormDto);
            if(currentUser.startsWith("A")){
               loadAdminDash(userName);
            } else if (currentUser.startsWith("E")) {
                loadEmployeeDash(userName);
            }
        }
        else {
            incorrect.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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


}
