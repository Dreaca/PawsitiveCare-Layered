package model;

import Db.DbConnection;
import Dto.ScheduleDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleModel {
    public List<ScheduleDto> getScheduleData() throws SQLException {
        List<ScheduleDto> dtos = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM schedule");
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            String vetName = getVetSheduleData(resultSet.getString("scheduleId"));
            dtos.add(
                    new ScheduleDto(
                    resultSet.getString("scheduleId"),
                            resultSet.getDate("date").toLocalDate(),
                    resultSet.getString("duration"),
                    resultSet.getString("time"),
                    vetName
                    )
            );
        }
        return dtos;
    }

    private String getVetSheduleData(String sheduleId) throws SQLException {
        VetModel model = new VetModel();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT vetId FROM vet_schedule WHERE scheduleId = ? ");
        pstm.setString(1,sheduleId);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return model.getVetName(resultSet.getString("vetId"));
        }else
        return null;
    }

    public boolean saveScheduleItem(ScheduleDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        var model = new VetModel();
        String vetId = model.getVetId(dto.getVetName());
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO schedule VALUES(?,?,?,?)");
        pstm.setString(1,dto.getScheduleId());
        pstm.setDate(2, Date.valueOf(dto.getDate()));
        pstm.setString(3,dto.getDuration());
        pstm.setTime(4, Time.valueOf(dto.getTime()));
        int i = pstm.executeUpdate();
        if(i>0){
            PreparedStatement pstm1 = connection.prepareStatement("INSERT INTO vet_schedule VALUES (?,?)");
            pstm1.setString(1,dto.getScheduleId());
            pstm1.setString(2,vetId);
            int i1 = pstm1.executeUpdate();
            return i1>0;
        }
        else return false;
    }

    public String getNextShedId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT scheduleId FROM schedule ORDER BY scheduleId DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return generate(resultSet.getString(1));
        }
        else return "S001";
    }

    private String generate(String string) {
        String[]ids = string.split("S0");
        int num = Integer.parseInt(ids[1]);
        num++;
        if(string.equals(null)){
            return "S001";
        }
        else {
            return "S00"+num;
        }
    }
}
