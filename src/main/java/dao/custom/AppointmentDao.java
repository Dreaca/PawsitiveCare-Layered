package dao.custom;

import dao.CrudDao;
import dto.AppointmentDto;
import dto.CustomerDto;
import entity.Appointment;

import java.sql.SQLException;

public interface AppointmentDao extends CrudDao<Appointment> {

    String getNextAppid() throws SQLException;
    String splitAppId(String nextId);

}
