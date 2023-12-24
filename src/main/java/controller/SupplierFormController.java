package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.Arrays;

public class SupplierFormController {

    @FXML
    private ComboBox cmbType;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colInvoice;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colSupName;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> coltype;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblSupplier;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierNAme;

    public void initialize(){
        setCellValueFactory();
        loadCmboBox();
        loadAllData();
    }

    private void setCellValueFactory() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("suppId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("suppName"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    private void loadAllData() {

    }

    private void loadCmboBox(){
        for (String type : Arrays.asList("Food","Medicine","Accessory","PetCare")) {
            cmbType.getItems().add(type);
        }
    }
    @FXML
    void addsupplierONAction(ActionEvent event) {

    }

    @FXML
    void searchOnAction(ActionEvent event) {

    }

    @FXML
    void searchSupplier(ActionEvent event) {

    }

    @FXML
    void updateSupplierOnAction(ActionEvent event) {

    }

}
