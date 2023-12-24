package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.ItemModel;
import model.SupplierModel;

import java.sql.SQLException;

public class SuppliyOrderFormController {
    public ComboBox cmbItemCode;
    public TextField txtItemCode;
    public ComboBox cmbSupplier;
    public TextField txtSupplierName;
    public TextField txtQty;
    public DatePicker dpkSelectDate;
    public Label lblIssuedDate;
    public Label lblSupOrderNum;

    public void placeOrderOnAction(ActionEvent actionEvent) {

    }

    public void cancelOrderOnAction(ActionEvent actionEvent) {

    }

    public void backOnAction(ActionEvent actionEvent) {

    }
    public void loadComboBoxes() throws SQLException {
        var model = new ItemModel();
         cmbItemCode.getItems().setAll(model.getItemcodes());
         var smodel = new SupplierModel();
         cmbSupplier.getItems().setAll(smodel.getSupplierIds());
    }
    public void loadOtherStuff(){

    }
    public void initialize() throws SQLException {
        loadComboBoxes();
    }
}
