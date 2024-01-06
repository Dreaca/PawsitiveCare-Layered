package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.OrderDao;
import db.DbConnection;
import dto.OrderDto;
import entity.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
    @Override
    public String generateNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return splitOrderID(resultSet.getString(1));
        }
        return "O001";
    }

    @Override
    public String splitOrderID(String string) {
        if(string != null){
            String[] b = string.split("O0");
            int id = Integer.parseInt(b[1]);
            id++;
            return "O00"+id;
        }
        else return "O001";
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(Orders dto) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("INSERT INTO orders VALUES (?,?,?)",dto.getOrderId(),dto.getCustId(),dto.getDate());
    }

    @Override
    public boolean update(Orders dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Orders search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
