package model;

import db.DbConnection;
import dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

    public CustomerModel() {
    }

    public static String getCustomerId(CustomerDto cus) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT custId FROM customer WHERE name = ?");
        pstm.setString(1,cus.getCustomerName());
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString("custId");
        }
        else return  "N/A";
    }

    public static String getCustomerName(String custId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT name FROM customer WHERE custId = ?");
        pstm.setString(1,custId);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return resultSet.getString("name");
        }
        else return "Not registered";
    }

    public static String getCustomerId(String text) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT custId FROM customer WHERE name = ?");
        pstm.setString(1,text);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        else return "Not registered";
    }

    public static String getContact(String custId) {
        return null;
    }


    public boolean saveCustomer(CustomerDto dto) throws SQLException {
        boolean flag;
        String sql = "INSERT INTO customer VALUES(?,?,?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getCustomerId());
        pstm.setString(2,dto.getCustomerName());
        pstm.setString(3,dto.getCustomerAddress());
        int i = pstm.executeUpdate();
        saveCustomerContact(dto);
        flag = i > 0;
        return flag;
    }

    private void saveCustomerContact(CustomerDto dto) throws SQLException {

        String sql = "INSERT INTO contact VALUES(?,?,?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getCustomerId());
        pstm.setString(2,ContactModel.generateContactId());
        pstm.setString(3,dto.getCustomerContact());
        pstm.executeUpdate();

    }

    public boolean deleteCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
                String sql = "DELETE FROM customer WHERE custId = ?";
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setString(1,id);
                int i = pstm.executeUpdate();
                return i > 0;
    }

    public CustomerDto searchCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE custId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet = pstm.executeQuery();
        CustomerDto dto = null;
        if(resultSet.next()){
            String custId = resultSet.getNString("custId");
            String name = resultSet.getString("name");
            String address = resultSet.getString("Address");
            String contact = ContactModel.getContact(custId);

            dto = new CustomerDto(custId,name,address,contact);
        }
        return dto;

    }

    public CustomerDto searchCustomerByFname(String fname) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE name LIKE ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,fname+"%");
        ResultSet resultSet = pstm.executeQuery();
        CustomerDto dto = null;
        if(resultSet.next()){
            String custId = resultSet.getNString("custId");
            String name = resultSet.getString("name");
            String address = resultSet.getString("Address");
            String contact = resultSet.getString("contact");

            dto = new CustomerDto(custId,name,address,contact);
        }
        return dto;
    }
    public CustomerDto searchCustomerByLname(String Lname) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE name LIKE ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,"% "+Lname);
        ResultSet resultSet = pstm.executeQuery();
        CustomerDto dto = null;
        if(resultSet.next()){
            String custId = resultSet.getNString("custId");
            String name = resultSet.getString("name");
            String address = resultSet.getString("Address");
            String contact = resultSet.getString("contact");

            dto = new CustomerDto(custId,name,address,contact);
        }
        return dto;
    }

    public CustomerDto searchCustomerByContact(String contact) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE contact = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,contact);
        ResultSet resultSet = pstm.executeQuery();
        CustomerDto dto = null;
        if(resultSet.next()){
            String custId = resultSet.getString("custId");
            String name = resultSet.getString("name");
            String address = resultSet.getString("Address");
            dto = new CustomerDto(custId,name,address,contact);
        }
        return dto;

    }

    public List<CustomerDto> getAllCustomer() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<CustomerDto> dto = new ArrayList<>();

        while (resultSet.next()){
            dto.add(
              new CustomerDto(
                      resultSet.getString(1),
                      resultSet.getString(2),
                      resultSet.getString(3),
                      resultSet.getString(4)
              )
            );
        }
        return dto;
    }

    public CustomerDto getCustomerDetail(String text) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM customer WHERE name = ?");
            pstm.setString(1,text);
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()){
                return new CustomerDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }
            return new CustomerDto("Not in System",null,"Not in System","Not in system");

    }
}
