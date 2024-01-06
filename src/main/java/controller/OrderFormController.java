package controller;

import bo.BOFactory;
import bo.custom.CustomerBo;
import bo.custom.ItemBo;
import bo.custom.OrdersBO;
import dto.CustomerDto;
import dto.ItemDto;
import dto.PlaceOrderDto;
import dto.Tm.AppointmentTm;
import dto.Tm.OrderTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public TextField txtCustomerContact;
    public Label lblDate;
    public TableColumn colAmount1;
    public ComboBox cmbCustomerName;

    private OrdersBO bo = (OrdersBO) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.ORDERS);
    private ItemBo item = (ItemBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.ITEM);
    private CustomerBo customerBo = (CustomerBo) BOFactory.getBOFactory().getBo(BOFactory.BoTypes.CUSTOMER);
    private ObservableList<OrderTm> oblist = FXCollections.observableArrayList();
    public void itemCodeOnAction() {
        ItemDto dto = null;
        try {
            dto = item.searchItem((String) cmbItemCode.getValue());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        txtDescription.setText(dto.getDescription());
        txtUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
    }
    public void btnAddItemOnAction(){
        String itemCode = (String) cmbItemCode.getValue();
        String desc = txtDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double uni = Double.parseDouble(txtUnitPrice.getText());
        double amount = qty*uni;
        double total = calculateTotal()+amount;
        lblNetTotal.setText(String.valueOf(total));
        int itemCount = Integer.parseInt(lblItemCount.getText());
        itemCount++;
        lblItemCount.setText(String.valueOf(itemCount));
//        int itemCount = 0;
//        String itemCode = (String) cmbItemCode.getValue();
//        String desc = txtDescription.getText();
//        int qty = Integer.parseInt(txtQty.getText());
//        double uni = Double.parseDouble(txtUnitPrice.getText());
//        double amount = qty*uni;
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
        oblist.add(order);
        tblOrder.setItems(oblist);
        txtQty.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
    }

    private double calculateTotal() {
        double total = 0;
        for (int i = 0; i < oblist.size(); i++) {
            double amount = oblist.get(i).getAmount();
            total = total+amount;
        }
        return total;
    }

    public void btnClearOnAction(){
        ObservableList<AppointmentTm> oblist = FXCollections.observableArrayList();
        oblist.add(null);
        tblOrder.setItems(oblist);
        tblOrder.refresh();
        cmbItemCode.promptTextProperty();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtCustomerId.clear();
        txtQty.clear();
        txtCustomerContact.clear();
        lblItemCount.setText("0");
        lblNetTotal.setText("0.0");

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
            if(bo.saveOrder(placeOrderDto)){
                createJasperReport(orderList);
                new Alert(Alert.AlertType.CONFIRMATION, "Order Saved").show();
                tblOrder.setItems(null);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void cancelOnAction(){
        cmbCustomerName.setValue(null);
        cmbItemCode.setValue(null);
        txtCustomerContact.clear();
        txtDescription.clear();
        txtQty.clear();
        txtUnitPrice.clear();
        txtCustomerId.clear();
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

    private void loadAllData() {

        try {
            lblOrderId.setText(bo.generateNextOrderId());
            cmbItemCode.getItems().setAll(item.getItemcodes());
            cmbCustomerName.getItems().setAll(bo.getCustomerNameList());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }
    private void createJasperReport(List<OrderTm> orderList) {
        try {
            Map parameters = new HashMap<>();
            parameters.put("OrderId", lblOrderId.getText());
            parameters.put("NetTotal", lblNetTotal.getText());

            for (int i = 0; i < orderList.size(); i++) {
                parameters.put("code",orderList.get(i).getItemCode());
                parameters.put("name",orderList.get(i).getDescription());
                parameters.put("Qty",String.valueOf(orderList.get(i).getQty()));
                parameters.put("unitPrice",String.valueOf(orderList.get(i).getUnitPrice()));
                parameters.put("amount",String.valueOf(orderList.get(i).getAmount()));
            }

            InputStream ResourceAsStream = getClass().getResourceAsStream("/report/bill.jrxml");
            JasperDesign load = JRXmlLoader.load(ResourceAsStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(load);


            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();

        }
    }

    public void customreNameOnAction(ActionEvent actionEvent) {
        CustomerDto dt = null;
        try {
            dt = customerBo.searchCustomerByname((String) cmbCustomerName.getValue());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        txtCustomerId.setText(dt.getCustomerId());
        txtCustomerContact.setText(dt.getCustomerContact());
    }
}
