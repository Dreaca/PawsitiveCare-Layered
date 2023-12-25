package bo;

import dao.DaoFactory;
import dao.custom.AppointmentDao;
import dao.custom.Impl.AppointmentDAOImpl;
import dto.AppointmentDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentBoImpl implements AppointmentBo {
    private AppointmentDao dao = (AppointmentDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.APPOINTMENT);

    @Override
    public String getNextAppid() throws SQLException {
       return dao.getNextAppid();
    }

    @Override
    public boolean saveAppointment(AppointmentDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(dto);
    }

    @Override
    public ArrayList<AppointmentDto> getAllAppointments() throws SQLException, ClassNotFoundException {
        return dao.getAll();
    }
}
