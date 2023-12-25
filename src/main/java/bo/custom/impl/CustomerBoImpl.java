package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    private CustomerDao dao = (CustomerDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.CUSTOMER);

    @Override
    public List<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {
        return dao.getAll();
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return dao.update(customerDto);
    }

    @Override
    public CustomerDto searchCustomerByContact(String contact) throws SQLException {
        return dao.searchCustomerByContact(contact);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public CustomerDto searchCustomerByLastname(String lname) throws SQLException {
        return dao.searchCustomerByLname(lname);
    }

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(dto);
    }

    @Override
    public CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return dao.search(id);
    }

    @Override
    public CustomerDto searchCustomerByFirstname(String fname) throws SQLException {
        return dao.searchCustomerByFname(fname);
    }
}
