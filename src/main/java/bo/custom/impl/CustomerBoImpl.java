package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dto.CustomerDto;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    private CustomerDao dao = (CustomerDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.CUSTOMER);

    @Override
    public List<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = dao.getAll();
        ArrayList<CustomerDto> list =  new ArrayList<>();
        for (Customer c: all) {
            list.add(new CustomerDto(
                    c.getCustId(),
                    c.getName(),
                    c.getAddress(),
                    c.getContact()
            ));
        }
        return list;
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return dao.update(new Customer(customerDto.getCustomerId(),customerDto.getCustomerName(),customerDto.getCustomerAddress(),customerDto.getCustomerContact()));
    }

    @Override
    public CustomerDto searchCustomerByContact(String contact) throws SQLException {
        Customer customer = dao.searchCustomerByContact(contact);
        return new CustomerDto(customer.getCustId(),customer.getName(),customer.getAddress(),customer.getContact());
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public CustomerDto searchCustomerByLastname(String lname) throws SQLException {
        Customer customer = dao.searchCustomerByContact(lname);
        return new CustomerDto(customer.getCustId(),customer.getName(),customer.getAddress(),customer.getContact());
    }

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Customer(dto.getCustomerId(),dto.getCustomerName(),dto.getCustomerAddress(),dto.getCustomerContact()));
    }

    @Override
    public CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer search = dao.search(id);
        return new CustomerDto(search.getCustId(),search.getName(),search.getAddress(),search.getContact());
    }

    @Override
    public CustomerDto searchCustomerByFirstname(String fname) throws SQLException {
        Customer customer = dao.searchCustomerByContact(fname);
        return new CustomerDto(customer.getCustId(),customer.getName(),customer.getAddress(),customer.getContact());
    }

    @Override
    public CustomerDto searchCustomerByname(String name) throws SQLException {
        Customer customer = dao.searchCustomerByName(name);
        return new CustomerDto(customer.getCustId(),customer.getName(),customer.getAddress(),customer.getContact());
    }
}
