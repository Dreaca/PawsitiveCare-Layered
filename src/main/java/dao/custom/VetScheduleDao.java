package dao.custom;

import dao.CrudDao;
import dto.VetScheduleDto;
import entity.VetSchedule;

import java.sql.SQLException;

public interface VetScheduleDao extends CrudDao<VetSchedule> {
    public String getVetSheduleData(String sheduleId) throws SQLException;
}
