package controller;

import bo.custom.LoginBo;
import bo.BOFactory;
import bo.custom.EmployeeBo;
import dto.AdminDto;
import dto.EmployeeDto;
import dto.LoginFormDto;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class AdminFormController {
    public Label adminUserId1;
    public TextField txtName;
    public JFXButton btnUserName1;
    public Label lblUser;
    public Label lblNameSuccess;
    public Label lblUserNameSuccess;
    public Label lblUserIdSuccess;
    public Label lblPasswordSucess;
    public Label lblUpdateNICSuccess;
    private String user;
    public AnchorPane sidePane;
    public AnchorPane root;
    public JFXButton btnVet;
    public JFXButton btnCustomer;
    public JFXButton btnReport;
    public JFXButton btnSupplier;
    public JFXButton btnStock;
    public Label adminName;
    public Label adminUserId;
    public Label adminNIC;
    public Label adminPass;
    public TextField txtnewUserId;
    public TextField txtNewUSerName;
    public TextField txtnewNIc;
    public TextField txtOldPassword;
    public TextField txtNewPassWord;
    public TextField txtConfirmPAss;
    public JFXButton btnNEwUSerId;
    public JFXButton btnUserName;
    public JFXButton btnNic;
    public JFXButton btnPass;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnUser;
//    private LoginModel Lmodel = new LoginModel();
    private LoginBo adminBo = (LoginBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.LOGIN);
//    EmployeeModel model = new EmployeeModel();
    private EmployeeBo bo = (EmployeeBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.EMPLOYEE);
    public void initialize() throws SQLException {
    }

    @FXML
    void customerOnAction(ActionEvent event) throws IOException {
        changeColor(btnCustomer);
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/common/customerForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(root);
    }

    @FXML
    void employeeOnAction(ActionEvent event) throws IOException, SQLException {
        changeColor(btnEmployee);
        Parent empNode = FXMLLoader.load(this.getClass().getResource("/view/employeeManage/ManageEmployee.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(empNode);

    }

    @FXML
    void supplierOnAction(ActionEvent event) throws IOException {
        changeColor(btnSupplier);
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/AdminDash/supplierForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(root);

    }

    @FXML
    void userBtnOnAction(ActionEvent event) throws IOException {
        Alert logout = new Alert(Alert.AlertType.CONFIRMATION);
        logout.setTitle("Confirmation");
        logout.setContentText("Are you sure to logout from the system? ");
        logout.getButtonTypes().setAll(ButtonType.YES,ButtonType.CANCEL);
        Optional<ButtonType> result = logout.showAndWait();
        if (result.isPresent()&&result.get() == ButtonType.YES){
            logout();
        }
    }

    private void logout() throws IOException {
        AnchorPane logout;
        logout = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/common/loginForm.fxml"));
        Scene scene = new Scene(logout);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login");
        stage.show();

    }

    @FXML
    void vetOnAction(ActionEvent event) throws IOException {
        changeColor(btnVet);
        Parent vetNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/AdminDash/vetForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(vetNode);
    }

    public void manageStockOnAction(ActionEvent actionEvent) throws IOException {
        changeColor(btnStock);
        Parent stock = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/AdminDash/manageItemForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(stock);
    }

    public void setUSerName(String userName) throws SQLException {
        user = userName;
        btnUser.setText(userName);
        lblUser.setText(userName);
        setDashBoard();
    }
    public void changeColor(JFXButton btn){
        JFXButton btns[] = {btnStock,btnSupplier,btnCustomer,btnEmployee,btnVet};
        for (int i = 0; i < btns.length; i++) {
            btn.setStyle("-fx-background-color : transparent ;"+"-fx-border-color : faa80a ;"+"-fx-border-radius : 40;"+"-fx-background-radius : 40;");
            if(!btn.equals(btns[i])){
                btns[i].setStyle("-fx-background-color : transparent; "+"-fx-border-color : transparent;"+"-fx-border-radius : 40;"+"-fx-background-radius : 40;");
            }
        }
    }

    public void updateUSerIdonAction(ActionEvent actionEvent) {
        txtnewUserId.setVisible(true);
        btnNEwUSerId.setVisible(true);
    }

    public void updateNIConAction(ActionEvent actionEvent) {
        txtnewNIc.setVisible(true);
        btnNic.setVisible(true);
    }
    public void onAddNewUsrId(ActionEvent event){
        try {
            String oldUserName = btnUser.getText();
            String newUserId = txtnewUserId.getText();
            boolean b = bo.UpdateUserId(oldUserName,newUserId);
            if (b) {
                txtnewUserId.setVisible(false);
                btnNEwUSerId.setVisible(false);
                lblUserIdSuccess.setVisible(true);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void addNewUserName() throws SQLException {
        String userName = txtName.getText();
        String userIdText = adminUserId.getText();
        boolean b = adminBo.updateUserName(userName, userIdText);
        if (b) {
            txtName.setVisible(false);
            btnUserName1.setVisible(false);
            lblUserNameSuccess.setVisible(true);
        }
    }

    public void updatePassOnAction(ActionEvent actionEvent) {
        txtOldPassword.setVisible(true);
        txtNewPassWord.setVisible(true);
        txtConfirmPAss.setVisible(true);
        btnPass.setVisible(true);
    }
    public void addNewNIC() {
        String NIC = txtnewNIc.getText();
        String userId = adminUserId.getText();
        try {
            if (bo.UpdateNIC(userId,NIC)) {
                txtnewNIc.setVisible(false);
                btnNic.setVisible(false);
                lblUpdateNICSuccess.setVisible(true);
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewPassWordOnAction(ActionEvent event) throws SQLException {
        String userId = adminUserId.getText();
        String oldPasswordText = txtOldPassword.getText();
        if (adminBo.validatePasswords(userId,oldPasswordText)) {
            String newpW = txtNewPassWord.getText();
            String confPw = txtConfirmPAss.getText();
            if (newpW.equals(confPw)){
                boolean b = adminBo.updatePassword(userId, newpW);
                if (b) {
                    txtOldPassword.setVisible(false);
                    txtNewPassWord.setVisible(false);
                    txtConfirmPAss.setVisible(false);
                    btnPass.setVisible(false);
                    lblPasswordSucess.setVisible(true);
                }
                else new Alert(Alert.AlertType.ERROR,"Something Went Wrong !!!").show();
            }
            }
        else {
            new Alert(Alert.AlertType.ERROR,"Password Incorrect").show();
        }
    }
    public void setDashBoard() {
        AdminDto userAdmin = null;
        try {
            userAdmin = adminBo.getUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(user);
        EmployeeDto dto = userAdmin.getDto();
        System.out.println(dto+"Admin dash");
        LoginFormDto loginFormDto = userAdmin.getLoginFormDto();
        adminUserId1.setText(loginFormDto.getUserName());
        adminName.setText(dto.getEmpName());
        adminNIC.setText(dto.getNIC());
        adminUserId.setText(loginFormDto.getUserID());
        adminPass.setText(loginFormDto.getPassword());
    }

    public void addNewNameOnAction(ActionEvent event)  {
        String name = txtNewUSerName.getText();
        String userId = adminUserId.getText();
        boolean b = false;
        try {
            b = bo.updateName(name, userId);

        if (b) {
            txtNewUSerName.setVisible(false);
            btnUserName.setVisible(false);
            lblNameSuccess.setVisible(true);
        }
        else {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong").show();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUSerNameonAction(ActionEvent event) {
        txtName.setVisible(true);
        btnUserName1.setVisible(true);
        setDashBoard();
    }

    public void updateNameOnAction(ActionEvent event) {
        txtNewUSerName.setVisible(true);
        btnUserName.setVisible(true);
        setDashBoard();
    }

    public void logoutOnAction(ActionEvent actionEvent) {
        try {
            userBtnOnAction(actionEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exitOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException, SQLException {
        Stage window = (Stage) root.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashBoards/AdminDash/adminDash.fxml"));
        Scene scene = new Scene(loader.load());
        AdminFormController controller = loader.getController();
        controller.setUSerName(lblUser.getText());
        window.setScene(scene);
    }
}
