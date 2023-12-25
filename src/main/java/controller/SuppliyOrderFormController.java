package controller;

import bo.BOFactory;
import bo.custom.ItemBo;
import bo.custom.SupplierBo;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    private ItemBo itemBo = (ItemBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.ITEM);
    private SupplierBo sBo = (SupplierBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.SUPPLIER);

    public void placeOrderOnAction(ActionEvent actionEvent) {

    }

    public void cancelOrderOnAction(ActionEvent actionEvent) {

    }

    public void backOnAction(ActionEvent actionEvent) {

    }
    public void loadComboBoxes() {
        try {
            cmbItemCode.getItems().setAll(itemBo.getItemcodes());
            cmbSupplier.getItems().setAll(sBo.getSupplierIds());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void loadOtherStuff(){

    }
    public void initialize() throws SQLException {
        loadComboBoxes();
    }
}
