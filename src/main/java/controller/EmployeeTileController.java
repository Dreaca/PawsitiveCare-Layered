package controller;

import bo.BOFactory;
import bo.custom.EmployeeBo;
import dto.EmployeeDto;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import javax.sound.midi.Soundbank;
import java.sql.SQLException;

public class EmployeeTileController {
//    public ImageView imgEmpImg;
    public Label lblEmpName;
    public Label lblEmpId;
    public Label lblAddress;
    public Label lblContact;
    public Label lblUserId;
    public Label lblSalary;

    public Label lblNIC;
    private EmployeeBo bo = (EmployeeBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.EMPLOYEE);

    public void setEmployeeData(EmployeeDto employee) {
        lblEmpId.setText(employee.getEmpId());

//        System.out.println(employee);

        lblEmpName.setText(employee.getEmpName());
        lblAddress.setText(employee.getAddress());
        lblContact.setText(employee.getContact());
        lblSalary.setText(String.valueOf(employee.getSalary()));
        lblUserId.setText(employee.getUserId());
        lblNIC.setText(employee.getNIC());

    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException {
        String empId = lblEmpId.getText();
        boolean b = false;
        try {
            b = bo.deleteEmployee(empId);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (b) {
            new Alert(Alert.AlertType.INFORMATION,"Employee Deleted").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Employee Not Deleted").show();
        }

    }
}
