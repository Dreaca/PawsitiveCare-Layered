package model;

import Db.DbConnection;
import Dto.OrderDto;
import Dto.PlaceOrderDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;


public class PlaceOrderModel {
    private OrderDetailModel model = new OrderDetailModel();
    private  ItemModel itemModel = new ItemModel();
    private OrderDetailModel orderDetailModel = new OrderDetailModel();
    public boolean placeOrder(PlaceOrderDto placeOrderDto) throws SQLException {
        String orderId = placeOrderDto.getOrderId();
        String custId = placeOrderDto.getCustId();
        LocalDate date = placeOrderDto.getDate();
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            if (model.saveOrder(orderId, custId, date)) {
                if (itemModel.updateItem(placeOrderDto.getList())) {
                    if (orderDetailModel.saveOrderDetails(placeOrderDto.getOrderId(), placeOrderDto.getList())) {
                        connection.commit();
                    }
                }
            }
            connection.rollback();
        } catch (SQLException e) {
            connection.rollback();
        }
        finally {
            connection.setAutoCommit(true);
        }
        return true;
    }
}
