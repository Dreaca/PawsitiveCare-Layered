package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.OrderDetailDao;
import dto.OrderDetailDto;
import dto.Tm.OrderTm;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public boolean saveOrder(String orderId, String custId, LocalDate date) throws SQLException {
        return SQLUtil.execute("INSERT INTO orders VALUES(?,?,?)",orderId,custId,Date.valueOf(date));
    }

    @Override
    public boolean saveOrderDetails(String orderId, List<OrderTm> list) throws SQLException {
        for (OrderTm tm : list) {
            if (!saveOrderDetails(orderId,tm)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean saveOrderDetails(String orderId, OrderTm tm) throws SQLException {
        return SQLUtil.execute("INSERT INTO orderDetail VALUES(?,?,?,?)",orderId,tm.getItemCode(),tm.getQty(), tm.getUnitPrice());
    }

    @Override
    public ArrayList<OrderDetailDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(OrderDetailDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetailDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetailDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
