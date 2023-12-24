package Dao;

import Dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    String getCustomerId(CustomerDto cus) throws SQLException;

    String getCustomerName(String custId) throws SQLException;

    String getCustomerId(String text) throws SQLException;


    boolean saveCustomer(CustomerDto dto) throws SQLException;


    boolean deleteCustomer(String id) throws SQLException;

    CustomerDto searchCustomer(String id) throws SQLException;

    CustomerDto searchCustomerByFname(String fname) throws SQLException;

    CustomerDto searchCustomerByLname(String Lname) throws SQLException;

    CustomerDto searchCustomerByContact(String contact) throws SQLException;

    List<CustomerDto> getAllCustomer() throws SQLException;

    CustomerDto getCustomerDetail(String text) throws SQLException;
}
