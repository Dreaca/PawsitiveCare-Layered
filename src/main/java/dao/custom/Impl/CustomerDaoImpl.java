package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.CustomerDao;
import dto.CustomerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public String getCustomerId(CustomerDto cus) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT custId FROM customer WHERE name = ?",cus);
        if (resultSet.next()){
            return resultSet.getString("custId");
        }
        else return  "N/A";
    }
    @Override
    public String getCustomerName(String custId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT name FROM customer WHERE custId = ?",custId);
        if(resultSet.next()){
            return resultSet.getString("name");
        }
        else return "Not registered";
    }
    @Override
    public String getCustomerId(String text) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT custId FROM customer WHERE name = ?",text);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        else return "Not registered";
    }

    @Override
    public boolean save(CustomerDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO customer VALUES(?,?,?,?)",dto);
    }

    @Override
    public boolean update(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM customer WHERE custId = ?",id);
    }
    @Override
    public CustomerDto search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE custId = ?",id);
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
    @Override
    public CustomerDto searchCustomerByFname(String fname) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE name LIKE ? %",fname);
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
    @Override
    public CustomerDto searchCustomerByLname(String Lname) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE name LIKE %?",Lname);
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
    @Override
    public CustomerDto searchCustomerByContact(String contact) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE contact = ?",contact);
        CustomerDto dto = null;
        if(resultSet.next()){
            String cusId = resultSet.getString("custId");
            String name = resultSet.getString("name");
            String address = resultSet.getString("Address");
            dto = new CustomerDto(cusId,name,address,contact);
        }
        return dto;

    }
    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");
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

}
