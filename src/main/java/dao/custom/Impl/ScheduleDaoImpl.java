package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.ScheduleDao;
import db.DbConnection;
import entity.Schedule;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ScheduleDaoImpl implements ScheduleDao {
    @Override
    public ArrayList<Schedule> getAll() throws SQLException {
        ArrayList<Schedule> dtos = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM schedule");
        while (resultSet.next()){
//            String vetName = getVetSheduleData(resultSet.getString("scheduleId"));
            dtos.add(
                    new Schedule(
                            resultSet.getString("scheduleId"),
                            resultSet.getDate("date").toLocalDate(),
                            resultSet.getString("duration"),
                            resultSet.getTime("time").toLocalTime()
                    )
            );
        }
        return dtos;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        System.out.println("delete check");
        return SQLUtil.execute("DELETE FROM schedule WHERE scheduleId = ?",id);
    }



    @Override
    public boolean save(Schedule entity) throws SQLException {
        System.out.println("check shedule dao");
        return (SQLUtil.execute("INSERT INTO schedule VALUES(?,?,?,?)",entity.getScheduleId(),
                Date.valueOf(entity.getDate()),
                entity.getDuration(),
                Time.valueOf(entity.getTime())));
    }

    @Override
    public boolean update(Schedule entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Schedule search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getNextShedId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT scheduleId FROM schedule ORDER BY scheduleId DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return generate(resultSet.getString(1));
        }
        else return "S001";
    }

    @Override
    public String generate(String string) {
        String[]ids = string.split("S0");
        int num = Integer.parseInt(ids[1]);
        num++;
        if(string == null){
            return "S001";
        }
        else {
            return String.format("S%03d",num);
        }
    }

    @Override
    public void searchSchedule(String vetName, LocalDate date) {

    }
}
