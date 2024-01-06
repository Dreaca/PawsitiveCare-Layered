package bo.custom.impl;

import bo.custom.AppointmentBo;
import dao.DaoFactory;
import dao.custom.AppointmentDao;
import dao.custom.CustomerDao;
import dto.AppointmentDto;
import dto.CustomerDto;
import entity.Appointment;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentBoImpl implements AppointmentBo {
    private AppointmentDao dao = (AppointmentDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.APPOINTMENT);
    private CustomerDao customerDao = (CustomerDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.CUSTOMER);
    @Override
    public String getNextAppid() throws SQLException {
       return dao.getNextAppid();
    }

    @Override
    public boolean saveAppointment(AppointmentDto dto) throws SQLException, ClassNotFoundException {
        String customerId = customerDao.getCustomerId(dto.getCustomerName());
        if(customerId.equals("Unregistered")|customerId.equals("Not Registered")) return false;
        else return dao.save(new Appointment(dto.getAppId(),
                        customerId,
                String.valueOf(dto.getType()),
                dto.getTime(),
                dto.getDate(),
                dto.getPrice()
                ));
    }

    @Override
    public ArrayList<AppointmentDto> getAllAppointments() throws SQLException, ClassNotFoundException {
        ArrayList<Appointment> all = dao.getAll();
        ArrayList<AppointmentDto> dtos = new ArrayList<>();
        for (Appointment a: all) {
            Customer cusName = customerDao.search(a.getCustId());
            dtos.add(new AppointmentDto(a.getAppId(),cusName.getName(),cusName.getContact(), AppointmentDto.AppType.getvalueOf(a.getType()),a.getTime(),a.getDate(),a.getPrice()));
        }
        return dtos;
    }

    @Override
    public String count(AppointmentDto.AppType appType) throws SQLException {
        return dao.count(String.valueOf(appType));
    }

    @Override
    public String countAll() throws SQLException {
        return dao.countAl();
    }

    @Override
    public CustomerDto getCustomer(String text) throws SQLException {
        Customer customer = customerDao.searchCustomerByFname(text);
        return new CustomerDto(customer.getCustId(),customer.getName(),customer.getAddress(),customer.getContact());

    }


}
