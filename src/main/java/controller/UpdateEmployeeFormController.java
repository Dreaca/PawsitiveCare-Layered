package controller;

import bo.BOFactory;
import bo.custom.EmployeeBo;
import bo.custom.LoginBo;
import bo.custom.UpdateEmployeeBo;
import dto.EmployeeDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class UpdateEmployeeFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private ComboBox<String> cmbEmpId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmpFname;

    @FXML
    private TextField txtEmpLName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtUserName;

    private EmployeeBo employeeBo = (EmployeeBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.EMPLOYEE);
    private UpdateEmployeeBo uBo = (UpdateEmployeeBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.UPEMP);
    public void initialize() throws SQLException {
        loadData();
    }

    private void loadData(){
        ObservableList<String> oblist = FXCollections.observableArrayList();
        List<EmployeeDto> list = null;
        try {
            list = employeeBo.getEmployees();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (EmployeeDto l : list) {
            oblist.add(l.getEmpId());
        }
        cmbEmpId.setItems(oblist);
    }

    @FXML
    void cancelOnAction(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String empId = cmbEmpId.getValue();
        String name = txtEmpFname.getText() + " " + txtEmpLName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        Double salary = Double.valueOf(txtSalary.getText());
        String userID = txtUserName.getText();
        String NIC = txtNic.getText();
        String pass = txtPass.getText();
        var dto = new EmployeeDto(empId, name, address, contact, salary, userID, NIC);
        try {
            if (uBo.updateData(userID,pass,dto)) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Update Completed").show();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}