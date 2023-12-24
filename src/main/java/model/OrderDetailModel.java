package model;

import Db.DbConnection;
import Dto.Tm.OrderTm;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrderDetailModel {
    public boolean saveOrder(String orderId, String custId, LocalDate date) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO orders VALUES(?,?,?)");
        pstm.setString(1,orderId);
        pstm.setString(2,custId);
        pstm.setDate(3, Date.valueOf(date));
        int i = pstm.executeUpdate();
        return i>0;
    }

    public boolean saveOrderDetails(String orderId, List<OrderTm> list) throws SQLException {
        for (OrderTm tm : list) {
            if (!saveOrderDetails(orderId,tm)){
                return false;
            }
        }
        return true;
    }

    private boolean saveOrderDetails(String orderId, OrderTm tm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO orderDetail VALUES(?,?,?,?)");
        pstm.setString(1,orderId);
        pstm.setString(2,tm.getItemCode());
        pstm.setInt(3,tm.getQty());
        pstm.setDouble(4,tm.getUnitPrice());
        return pstm.executeUpdate()>0;
    }
}
