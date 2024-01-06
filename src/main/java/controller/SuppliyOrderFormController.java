package controller;

import bo.BOFactory;
import bo.custom.SupplyOrderBo;
import dto.SupplyOrderDto;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.AbstractList;
import java.util.List;

public class SuppliyOrderFormController {
    public ComboBox cmbItemCode;
    public TextField txtItemCode;
    public ComboBox cmbSupplier;
    public TextField txtSupplierName;
    public TextField txtQty;
    public DatePicker dpkSelectDate;
    public Label lblIssuedDate;
    public Label lblSupOrderNum;
    public AnchorPane root;
    SupplyOrderBo bo = (SupplyOrderBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.SO);

    public void placeOrderOnAction(ActionEvent actionEvent) {
        String itemId = (String) cmbItemCode.getValue();
        loadOtherStuff(itemId);
        String Suppid = (String) cmbSupplier.getValue();
        int qty = Integer.parseInt(txtQty.getText());
        LocalDate date = dpkSelectDate.getValue();
        String supporderId = lblSupOrderNum.getText();
        try {
            bo.placeSupplyOrder(new SupplyOrderDto(supporderId,itemId,Suppid,qty,date));
            new Alert(Alert.AlertType.CONFIRMATION,"Order Placed Successfully").show();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            e.printStackTrace();
        }
    }

    public void cancelOrderOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.close();
    }

    public void backOnAction(ActionEvent actionEvent) {
        cancelOrderOnAction(actionEvent);
    }
    public void loadComboBoxes() {
        try {
            List<String> itemcodes = bo.getItemcodes();
            cmbItemCode.getItems().setAll(itemcodes);
            cmbSupplier.getItems().setAll(bo.getSupplierIds());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void loadOtherStuff(String value){
        cmbItemCode.setOnAction(actionEvent -> {
            try {
                txtItemCode.setText(bo.getItemName(value));
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
    public void initialize() throws SQLException {
        loadComboBoxes();
    }

    public void setSupplierId(String suppId) {
        cmbSupplier.setValue(suppId);
        try {
            txtSupplierName.setText(bo.getSupplierName(suppId));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
