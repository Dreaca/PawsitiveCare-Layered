package controller;

import Dto.ItemDto;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ItemModel;

import java.sql.SQLException;

public class AddNewItemController {

    public Label lblItemCode;
    public TextField txtDescription;
    public TextField txtQtO;
    public TextField txtUnitPrice;
    public AnchorPane root;
    ItemModel model = new ItemModel();

    public void cancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String itemCode = lblItemCode.getText();
        String desc = txtDescription.getText();
        int QTO = Integer.parseInt(txtQtO.getText());
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());
        var dto = new ItemDto(itemCode,desc,QTO,unitPrice);
        try {
            if(model.saveItem(dto)){
                new Alert(Alert.AlertType.CONFIRMATION,"Item Saved !!!").show();
                Stage stage = (Stage) root.getScene().getWindow();
                stage.close();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void initialize() throws SQLException {
        setLabel();
    }
    public void setLabel() throws SQLException {
        lblItemCode.setText(model.getNextItemCode());
    }
}
