package bo.custom;

import bo.custom.SuperBO;
import dao.SuperDao;
import dto.PlaceOrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrdersBO extends SuperBO {
    boolean saveOrder(PlaceOrderDto placeOrderDto) throws SQLException,ClassNotFoundException;

    String generateNextOrderId() throws SQLException;

    List<String> getCustomerNameList() throws SQLException, ClassNotFoundException;
}
