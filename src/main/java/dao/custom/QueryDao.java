package dao.custom;

import dao.SuperDao;
import entity.VetScheduleJoin;

import java.sql.SQLException;
import java.util.List;

public interface QueryDao extends SuperDao {
    List<VetScheduleJoin> getScheduleData() throws SQLException;
}
