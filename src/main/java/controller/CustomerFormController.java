package controller;

import Dto.CustomerDto;
import Dto.Tm.CustomerTm;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerModel;

import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {
    public TextField txtCusAddress;
    public JFXButton btnClear;
    @FXML
    private JFXButton btnDeleteCustomer;

    @FXML
    private JFXButton btnSaveCustomer;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colCustomerAddress;

    @FXML
    private TableColumn<?, ?> colCustomerContact;

    @FXML
    private TableColumn<?, ?> colCustomerID;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colCustomerPets;

    @FXML
    private RadioButton rdbtnCustomer2ndNum;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtContact2nd;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtCustomerFname;

    @FXML
    private TextField txtCustomerID;

    @FXML
    private TextField txtCustomerLname;

    private CustomerModel customerModel = new CustomerModel();
    public void initialize(){
        setCellValueFactory();
        loadAllCustomer();
    }

    private void loadAllCustomer() {
        var model = new CustomerModel();

        ObservableList<CustomerTm> oblist = FXCollections.observableArrayList();

        try {
            List<CustomerDto> list = model.getAllCustomer();

            for (CustomerDto d : list) {
                oblist.add(
                  new CustomerTm(
                          d.getCustomerId(),
                          d.getCustomerName(),
                          d.getCustomerAddress(),
                          d.getCustomerContact(),
                          d.getCustomerContact2(),
                          d.getCustomerPet()
                  )
                );
            }
            tblCustomer.setItems(oblist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void setCellValueFactory() {
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colCustomerContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtCustomerID.getText();
        String name = txtCustomerFname.getText()+txtCustomerLname.getText();
        String address = txtCusAddress.getText();
        String contact = txtContactNo.getText();

    }

    @FXML
    void customerContactSearch(ActionEvent event) {
        String contact = txtCustomerFname.getText();
        try {
            CustomerDto customerDto = customerModel.searchCustomerByContact(contact);
            if (customerDto != null){
                String [] name = customerDto.getCustomerName().split(" ");
                txtCustomerID.setText(customerDto.getCustomerId());
                txtCustomerFname.setText(name[0]);
                txtCustomerLname.setText(name[1]);
                txtCusAddress.setText(customerDto.getCustomerAddress());
                txtContactNo.setText(customerDto.getCustomerContact());
            }else{
                new Alert(Alert.AlertType.ERROR,"Customer Not Found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    @FXML
    void customerDeleteOnAction(ActionEvent event) {
        String id = txtCustomerID.getText();
        boolean isDeleted = false;
        try {
            isDeleted = customerModel.deleteCustomer(id);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted !").show();
            }
            else{
                new Alert(Alert.AlertType.INFORMATION,"Customer Not Deleted !").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }

    }

    @FXML
    void customerLnameSearchOnAction(ActionEvent event) {
        String Lname = txtCustomerLname.getText();
        try {
            CustomerDto customerDto = customerModel.searchCustomerByLname(Lname);
            if (customerDto != null){
                String [] name = customerDto.getCustomerName().split(" ");
                txtCustomerID.setText(customerDto.getCustomerId());
                txtCustomerFname.setText(name[0]);
                txtCustomerLname.setText(name[1]);
                txtCusAddress.setText(customerDto.getCustomerAddress());
                txtContactNo.setText(customerDto.getCustomerContact());
            }else{
                new Alert(Alert.AlertType.ERROR,"Customer Not Found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void customerSaveOnaction(ActionEvent event)  {
        String Id = txtCustomerID.getText();
        String Fname = txtCustomerFname.getText();
        String Lname = txtCustomerLname.getText();
        String Address = txtCusAddress.getText();
        String contact = txtContactNo.getText();
        String fullname = Fname+" "+Lname;


        var dto = new CustomerDto(Id,fullname,Address,contact);
        try{
            boolean isSaved = customerModel.saveCustomer(dto);
            tblCustomer.refresh();
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved Successfully").show();
                clearFields();
            }
        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();;
        }

    }



    private void clearFields() {
        txtCustomerID.clear();
        txtCusAddress.clear();
        txtCustomerFname.clear();
        txtCustomerLname.clear();
        txtContactNo.clear();
        txtContact2nd.clear();
    }

    @FXML
    void customerSearchOnaction(ActionEvent event) {
        String id = txtCustomerID.getText();
        try {
            CustomerDto customerDto = customerModel.searchCustomer(id);
            if (customerDto != null){
                String [] name = customerDto.getCustomerName().split(" ");
                txtCustomerID.setText(customerDto.getCustomerId());
                txtCustomerFname.setText(name[0]);
                txtCustomerLname.setText(name[1]);
                txtCusAddress.setText(customerDto.getCustomerAddress());
                txtContactNo.setText(customerDto.getCustomerContact());
            }else{
                new Alert(Alert.AlertType.ERROR,"Customer Not Found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void txtCustomerFnameSearch(ActionEvent event) {
        String Fname = txtCustomerFname.getText();
        try {
            CustomerDto customerDto = customerModel.searchCustomerByFname(Fname);
            if (customerDto != null){
                String [] name = customerDto.getCustomerName().split(" ");
                txtCustomerID.setText(customerDto.getCustomerId());
                txtCustomerFname.setText(name[0]);
                txtCustomerLname.setText(name[1]);
                txtCusAddress.setText(customerDto.getCustomerAddress());
                txtContactNo.setText(customerDto.getCustomerContact());
            }else{
                new Alert(Alert.AlertType.ERROR,"Customer Not Found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
}
