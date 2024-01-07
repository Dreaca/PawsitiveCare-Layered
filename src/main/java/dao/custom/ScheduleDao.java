package dao.custom;

import dao.CrudDao;
import dto.ScheduleDto;
import entity.Schedule;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleDao extends CrudDao<Schedule> {

//    String getVetSheduleData(String sheduleId) throws SQLException;
    String getNextShedId() throws SQLException;
    String generate(String string);

    void searchSchedule(String vetName, LocalDate date);
}
