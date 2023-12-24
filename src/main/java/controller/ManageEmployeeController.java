package controller;

import Dto.EmployeeDto;
import Dto.Tm.EmployeeTm;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.EmployeeModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageEmployeeController {

    public JFXButton btnAddEmployee;
    public JFXButton btnUpdateEmployee;
    public TableView tblEmployee;
    public TableColumn colPhoto;
    public TableColumn colID;
    public TableColumn colEmpName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colSalary;
    public TableColumn colDelete;
    public TableColumn userId;
    public HBox employeeHbox;
    @FXML
    private AnchorPane employeeAnchor;

    static Stage stage = new Stage();
    public void initialize() throws IOException, SQLException {

//        setCellValueFactory();
        loadAllEmployees();


    }

    private void loadAllEmployees() throws IOException, SQLException {
        List<EmployeeDto> dtos= EmployeeModel.getEmployeeDtos();
        for (EmployeeDto dto: dtos) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/employeeManage/EmployeeTile.fxml"));
            Parent tile = loader.load();
            EmployeeTileController controller = loader.getController();
            controller.setEmployeeData(dto);
            AnchorPane e = new AnchorPane();
            e.setStyle("-fx-background-color : White ;");
            e.setPrefSize(300,150);
            e.getChildren().add(tile);
            this.employeeHbox.getChildren().add(e);

        }
    }

/*
    private void setCellValueFactory(){
//        colPhoto.setCellValueFactory(new PropertyValueFactory<>("photo"));
        colID.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }
    public void loadAllEmployees(){
        var model = new EmployeeModel();

        ObservableList<EmployeeTm> oblist = FXCollections.observableArrayList();

        try {
            List<EmployeeDto> list = model.getEmployeeDtos();
            for (EmployeeDto d: list) {
                oblist.add(
                new EmployeeTm(
                        d.getEmpId(),
                        d.getName(),
                        d.getAddress(),
                        d.getContact(),
                        d.getSalary(),
                        d.getUserId()
                )
                );
            }
            tblEmployee.setItems(oblist);
            tblEmployee.refresh();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
*/


    public void addnewEmployee(ActionEvent actionEvent) throws IOException, SQLException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/employeeManage/addNewEmployeeForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        if(AddNewEmployeeFormController.savedEmployee){
            tblEmployee.refresh();
        }
    }
    public void closeWindowAddEmployee(){
        if(stage != null){
            stage.close();
        }
    }
    public void updateOnAction() throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/employeeManage/updateEmployeeForm.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
