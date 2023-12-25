package bo;

import dto.AppointmentDto;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentBo extends SuperBO{
    String getNextAppid() throws SQLException;

    boolean saveAppointment(AppointmentDto dto) throws SQLException, ClassNotFoundException;

    List<AppointmentDto> getAllAppointments() throws SQLException, ClassNotFoundException;
}
