package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.ScheduleDao;
import db.DbConnection;
import entity.Schedule;

import java.sql.*;
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
                            resultSet.getString("time")
                    )
            );
        }
        return dtos;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }



    @Override
    public boolean save(Schedule entity) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        var model = new VetModel();
        String vetId = model.getVetId(entity.getVetName());
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO schedule VALUES(?,?,?,?)");
        pstm.setString(1,entity.getScheduleId());
        pstm.setDate(2, Date.valueOf(entity.getDate()));
        pstm.setString(3,entity.getDuration());
        pstm.setTime(4, Time.valueOf(entity.getTime()));
        int i = pstm.executeUpdate();
        if(i>0){
            PreparedStatement pstm1 = connection.prepareStatement("INSERT INTO vet_schedule VALUES (?,?)");
            pstm1.setString(1,entity.getScheduleId());
            pstm1.setString(2,vetId);
            int i1 = pstm1.executeUpdate();
            return i1>0;
        }
        else return false;
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
            return "S00"+num;
        }
    }
}
