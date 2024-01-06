package controller;

import bo.BOFactory;
import bo.custom.ItemBo;
import dto.ItemDto;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class UpdateItemFormController {
    public ComboBox cmbItemCode;
    public TextField description;
    public TextField txtQTO;
    public TextField txtUnitPrice;
    public AnchorPane root;
//    private ItemModel model = new ItemModel();
    private ItemBo bo = (ItemBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.ITEM);
    public void initialize(){
        loadComboBox();
        cmbItemCode.setOnAction(event -> handleComboBoxSelection((ActionEvent) event));
    }
    private void handleComboBoxSelection(ActionEvent event) {
        String selectedCode = (String) cmbItemCode.getValue();
        if (selectedCode != null) {
            try {
                ItemDto selectedItem = bo.searchItem(selectedCode); // Implement this method in your BO class
                if (selectedItem != null) {
                    // Fill the text fields with the selected item details
                    description.setText(selectedItem.getDescription());
                    txtQTO.setText(String.valueOf(selectedItem.getQtyOnHand()));
                    txtUnitPrice.setText(String.valueOf(selectedItem.getUnitPrice()));
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadComboBox() {
        try {
            List<String> list = bo.getItemcodes();
            cmbItemCode.getItems().setAll(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void cancelOnAction(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    public  void saveUpdateOnAction(){
        String itemCode = (String) cmbItemCode.getValue();
        String description = this.description.getText();
        int QTO = Integer.parseInt(txtQTO.getText());
        double unitprice = Double.parseDouble(txtUnitPrice.getText());
        var dto = new ItemDto(itemCode,description,QTO,unitprice);
        try {
            if(bo.updateItem(dto)){
                new Alert(Alert.AlertType.CONFIRMATION,"Item Updated",ButtonType.OK,ButtonType.CANCEL).show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
