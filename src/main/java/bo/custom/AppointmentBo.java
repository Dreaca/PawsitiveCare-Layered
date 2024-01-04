package bo.custom;

import dto.AppointmentDto;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentBo extends SuperBO{
    String getNextAppid() throws SQLException;

    boolean saveAppointment(AppointmentDto dto) throws SQLException, ClassNotFoundException;

    List<AppointmentDto> getAllAppointments() throws SQLException, ClassNotFoundException;

    String count(AppointmentDto.AppType appType) throws SQLException;

    String countAll() throws SQLException;
}
