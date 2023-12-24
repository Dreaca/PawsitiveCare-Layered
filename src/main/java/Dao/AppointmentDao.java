package Dao;

import Dto.AppointmentDto;
import Dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentDao {
    boolean addAppointment(AppointmentDto dto, CustomerDto cus) throws SQLException;
    String getNextAppid() throws SQLException;
    String splitAppId(String nextId);
    List<AppointmentDto> getAllAppointments() throws SQLException;
}
