package dao.custom;

import db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {
   static Connection connection;

    static {
        try {
            connection = DbConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  static boolean setAutoCommit(boolean stat) throws SQLException, ClassNotFoundException {

            if(stat){
                connection.setAutoCommit(true);
                return  true;
            }
            else {
                connection.setAutoCommit(false);
                return false;
            }
    }
    public static void  rollback() throws SQLException {
        connection.rollback();
    }

    public static void commit() throws SQLException {
        connection.commit();
    }
}
