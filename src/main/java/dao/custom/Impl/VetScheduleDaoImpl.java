package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.VetScheduleDao;
import db.DbConnection;
import dto.VetScheduleDto;
import entity.VetSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VetScheduleDaoImpl implements VetScheduleDao {
    @Override
    public ArrayList<VetSchedule> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<VetSchedule> list = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM vet_schedule");
        while (rst.next()){
            list.add(new VetSchedule(
                    rst.getString(1),
                    rst.getString(2)
            ));
        }
        return list;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(VetSchedule entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(VetSchedule entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public VetSchedule search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getVetSheduleData(String sheduleId) throws SQLException {
//        VetModel model = new VetModel();
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT vetId FROM vet_schedule WHERE scheduleId = ? ");
            pstm.setString(1,sheduleId);
            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                return (resultSet.getString("vetId"));
            }else
                return null;

    }
}
