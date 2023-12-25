package bo;

import dao.custom.AppointmentDao;
import dao.custom.Impl.AppointmentDAOImpl;
import dto.AppointmentDto;

import java.sql.SQLException;

public class AddNewAppointmentBoImpl implements AddNewAppointmentBo{
    private AppointmentDao dao = new AppointmentDAOImpl();

    @Override
    public String getNextAppid() throws SQLException {
       return dao.getNextAppid();
    }

    @Override
    public boolean saveAppointment(AppointmentDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(dto);
    }
}
