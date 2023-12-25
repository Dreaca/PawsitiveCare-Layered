package dao.custom;

import dao.CrudDao;
import dto.ScheduleDto;
import entity.Schedule;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.List;

public interface ScheduleDao extends CrudDao<Schedule> {

//    String getVetSheduleData(String sheduleId) throws SQLException;
    String getNextShedId() throws SQLException;
    String generate(String string);
}
