package dao.custom;

import dao.CrudDao;
import dto.OrderDto;
import entity.Orders;

import java.sql.SQLException;

public interface OrderDao extends CrudDao<Orders> {
    String generateNextOrderId() throws SQLException;
    String splitOrderID(String string);
}
