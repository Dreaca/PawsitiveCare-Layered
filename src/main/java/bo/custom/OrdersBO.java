package bo.custom;

import bo.custom.SuperBO;
import dao.SuperDao;
import dto.PlaceOrderDto;

import java.sql.SQLException;

public interface OrdersBO extends SuperBO {
    boolean saveOrder(PlaceOrderDto placeOrderDto) throws SQLException,ClassNotFoundException;

    String generateNextOrderId() throws SQLException;
}
