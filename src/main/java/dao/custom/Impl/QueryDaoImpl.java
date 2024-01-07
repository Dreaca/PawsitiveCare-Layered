package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.QueryDao;
import entity.VetScheduleJoin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDaoImpl implements QueryDao {
    @Override
    public List<VetScheduleJoin> getScheduleData() throws SQLException {
        List<VetScheduleJoin> list = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT DISTINCT a.scheduleId, a.date, a.time, a.duration, v.name\n" +
                "FROM schedule a\n" +
                "INNER JOIN vet_schedule vs ON a.scheduleId = vs.scheduleId\n" +
                "INNER JOIN veterinarian v ON vs.vetid = v.vetid;");
        while (rst.next()){
            list.add(
                    new VetScheduleJoin(rst.getString("scheduleId"),
                            rst.getDate("date").toLocalDate(),
                            rst.getTime("time").toLocalTime(),
                            rst.getString("duration"),
                            rst.getString("name"))
            );
        }
        return list;
    }
}
