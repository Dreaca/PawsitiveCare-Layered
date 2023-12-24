package controller;

import Dto.CustomerDto;
import Dto.ItemDto;
import Dto.OrderDto;
import Dto.PlaceOrderDto;
import Dto.Tm.OrderTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.CustomerModel;
import model.ItemModel;
import model.OrderModel;
import model.PlaceOrderModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderFormController {
    public TableView tblOrder;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colAmount;
    public Label lblOrderId;
    public ComboBox cmbItemCode;
    public TextField txtUnitPrice;
    public TextField txtQty;
    public TextField txtDescription;
    public Label lblItemCount;
    public Label lblNetTotal;
    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerContact;
    public Label lblDate;

    private OrderModel model = new OrderModel();

    private PlaceOrderModel placeOrderModel = new PlaceOrderModel();
    private ItemModel itemModel = new ItemModel();
    private ObservableList<OrderTm> oblist = FXCollections.observableArrayList();
    public void itemCodeOnAction() throws SQLException {
        var dto = itemModel.getItemDesc((String) cmbItemCode.getValue());
        txtDescription.setText(dto.getDescription());
        txtUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
    }
    public void btnAddItemOnAction(){
        int itemCount = 0;
        String itemCode = (String) cmbItemCode.getValue();
        String desc = txtDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double uni = Double.parseDouble(txtUnitPrice.getText());
        double amount = qty*uni;
//        if (!oblist.isEmpty()){
//            for (int i = 0; i < tblOrder.getItems().size(); i++) {
//                if(colItemCode.getCellData(i).equals(itemCode)){
////                    int col_q = (int) colQty.getCellData(i);
////                    qty += col_q;
////                    amount = uni*col_q;
////                    oblist.get(i).setQty(qty);
////                    oblist.get(i).setAmount(amount);
//                    calculateTotal();
//                    tblOrder.refresh();
//                    itemCount++;
//                    lblItemCount.setText(String.valueOf(itemCount));
//                    return;
//                }
//            }
//        }
        var order = new OrderTm(itemCode,desc,qty,uni,amount);
        System.out.println(order);
        oblist.add(order);
        tblOrder.setItems(oblist);
        txtQty.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        cmbItemCode.promptTextProperty();
    }

    private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            total +=(double) colAmount.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(total));
    }

    public void btnClearOnAction(){
        
    }
    public void placeOrderOnAction(){
        String orderId = lblOrderId.getText();
        String custId = txtCustomerId.getText();
        LocalDate date = LocalDate.parse(lblDate.getText());


        List<OrderTm> orderList = new ArrayList<>();
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            OrderTm ordertm = oblist.get(i);
            orderList.add(ordertm);
        }
        var placeOrderDto = new PlaceOrderDto(orderId,custId,date,orderList);
        try {
            if(placeOrderModel.placeOrder(placeOrderDto)){
                new Alert(Alert.AlertType.CONFIRMATION, "Order Saved").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void cancelOnAction(){
        
    }
    public void customerOnAction() throws SQLException {
        var cus = new CustomerModel();
        CustomerDto dt = cus.getCustomerDetail(txtCustomerName.getText());
        txtCustomerId.setText(dt.getCustomerId());
        txtCustomerContact.setText(dt.getCustomerContact());
    }
    public void addNewCustomerOnAction() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashBoards/common/customerForm.fxml"))));
        stage.show();
    }
    public void initialize() throws SQLException {
        setCellValueFactory();
        loadAllData();
    }

    private void loadAllData() throws SQLException {

        lblOrderId.setText(model.generateNextOrderId());
        cmbItemCode.getItems().setAll(itemModel.getItemCodes());
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }
}
