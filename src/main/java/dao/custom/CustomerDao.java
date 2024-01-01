package dao.custom;

import dao.CrudDao;
import dto.CustomerDto;
import entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao extends CrudDao<Customer> {
    String getCustomerId(Customer cus) throws SQLException;

    String getCustomerName(String custId) throws SQLException;

    String getCustomerId(String text) throws SQLException;


    CustomerDto searchCustomerByFname(String fname) throws SQLException;

    CustomerDto searchCustomerByLname(String Lname) throws SQLException;

    CustomerDto searchCustomerByContact(String contact) throws SQLException;


}
