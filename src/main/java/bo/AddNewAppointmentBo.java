package bo;

import dto.AppointmentDto;

import java.sql.SQLException;

public interface AddNewAppointmentBo extends SuperBO{
    String getNextAppid() throws SQLException;

    boolean saveAppointment(AppointmentDto dto) throws SQLException, ClassNotFoundException;
}
