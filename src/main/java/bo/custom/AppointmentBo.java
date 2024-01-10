package bo.custom;

import dto.AppointmentDto;
import dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentBo extends SuperBO{
    String getNextAppid() throws SQLException;

    boolean saveAppointment(AppointmentDto dto) throws SQLException, ClassNotFoundException;

    List<AppointmentDto> getAllAppointments() throws SQLException, ClassNotFoundException;

    String count(AppointmentDto.AppType appType) throws SQLException;

    String countAll() throws SQLException;


    CustomerDto getCustomer(String text) throws SQLException;

    boolean deleteAppointment(String appId) throws SQLException, ClassNotFoundException;

    AppointmentDto getAppointment(String appId) throws SQLException, ClassNotFoundException;

    boolean updateAppointment(AppointmentDto dto) throws SQLException, ClassNotFoundException;
}
