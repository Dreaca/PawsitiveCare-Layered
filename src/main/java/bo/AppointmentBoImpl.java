package bo;

import dao.DaoFactory;
import dao.custom.AppointmentDao;
import dao.custom.CustomerDao;
import dao.custom.Impl.AppointmentDAOImpl;
import dao.custom.Impl.CustomerDaoImpl;
import dto.AppointmentDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        return dao.save(new AppointmentDto(dto.getAppId(),dto.getContact(),dto.getType(),dto.getTime(),dto.getTime(),customerId));
    }

    @Override
    public ArrayList<AppointmentDto> getAllAppointments() throws SQLException, ClassNotFoundException {
        return dao.getAll();
    }
}
