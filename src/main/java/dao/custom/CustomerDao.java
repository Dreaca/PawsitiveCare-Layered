package dao.custom;

import dao.CrudDao;
import entity.Customer;

import java.sql.SQLException;

public interface CustomerDao extends CrudDao<Customer> {
    String getCustomerId(Customer cus) throws SQLException;

    String getCustomerName(String custId) throws SQLException;

    String getCustomerId(String text) throws SQLException;


    Customer searchCustomerByFname(String fname) throws SQLException;

    Customer searchCustomerByLname(String Lname) throws SQLException;

    Customer searchCustomerByContact(String contact) throws SQLException;


    Customer searchCustomerByName(String name) throws SQLException;
}
