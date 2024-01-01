package dao.custom;

import dao.CrudDao;
import dto.OrderDetailDto;
import dto.Tm.OrderTm;
import entity.OrderDetail;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface OrderDetailDao extends CrudDao<OrderDetail> {
    boolean saveOrder(String orderId, String custId, LocalDate date) throws SQLException;
    boolean saveOrderDetails(String orderId, List<OrderTm> list) throws SQLException;
    boolean saveOrderDetails(String orderId, OrderTm tm) throws SQLException;
}
