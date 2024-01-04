package bo.custom;

import dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo extends SuperBO{
    List<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException;

    CustomerDto searchCustomerByContact(String contact) throws SQLException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    CustomerDto searchCustomerByLastname(String lname) throws SQLException;

    boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException;

    CustomerDto searchCustomerByFirstname(String fname) throws SQLException;

    CustomerDto searchCustomerByname(String name) throws SQLException;

    String getCount() throws SQLException;
}
