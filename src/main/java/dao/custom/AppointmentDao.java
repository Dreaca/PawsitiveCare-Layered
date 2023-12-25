package dao.custom;

import dao.CrudDao;
import dto.AppointmentDto;
import dto.CustomerDto;

import java.sql.SQLException;

public interface AppointmentDao extends CrudDao<AppointmentDto> {

    String getNextAppid() throws SQLException;
    String splitAppId(String nextId);

}
