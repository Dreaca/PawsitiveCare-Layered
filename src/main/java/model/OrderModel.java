package model;

import Db.DbConnection;
import Dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {
    public String generateNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return splitOrderID(resultSet.getString(1));
        }
        return "O001";
    }

    private String splitOrderID(String string) {
        if(!string.equals(null)){
            String b [] = string.split("O0");
            int id = Integer.parseInt(b[1]);
            id++;
            return "O00"+id;
        }
        else return "O001";
    }


}
