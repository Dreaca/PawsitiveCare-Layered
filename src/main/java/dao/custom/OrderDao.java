package dao.custom;

import dao.CrudDao;
import dto.OrderDto;

import java.sql.SQLException;

public interface OrderDao extends CrudDao<OrderDto> {
    String generateNextOrderId() throws SQLException;
    String splitOrderID(String string);
}
