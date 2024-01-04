package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.AppointmentDao;
import dto.AppointmentDto;
import dto.CustomerDto;
import entity.Appointment;

import java.sql.*;
import java.util.ArrayList;

public class AppointmentDAOImpl implements AppointmentDao {
    @Override
    public boolean save(Appointment entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO appointment VALUES(?,?,?,?,?)",entity.getAppId(),entity.getCustId(), entity.getType(),entity.getTime(),entity.getDate());
    }

    @Override
    public boolean update(Appointment dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Appointment search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getNextAppid() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT appId FROM appointment ORDER BY appId DESC LIMIT 1");
        if(resultSet.next()){
            return splitAppId(resultSet.getString(1));
        }
        return splitAppId(null);
    }

    @Override
    public String splitAppId(String nextId) {
        if(nextId!=null){
            String []appId = nextId.split("App");
            int num = Integer.parseInt(appId[1]);
            num++;
            return "App"+num;
        }
        else {
            return "App1";
        }
    }

    @Override
    public String count(String appType) throws SQLException {
        ResultSet rst =  SQLUtil.execute("SELECT COUNT(*) FROM appointment WHERE type = ?",appType);
        rst.next();
        return rst.getString(1);
    }

    @Override
    public String countAl() throws SQLException {
        ResultSet rst =  SQLUtil.execute("SELECT COUNT(*) FROM appointment");
        rst.next();
        return rst.getString(1);
    }

    @Override
    public ArrayList<Appointment> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM appointment");
        ArrayList<Appointment> dto = new ArrayList<>();
        while (resultSet.next()){
            dto.add(
                    new Appointment(
                            resultSet.getString("appId"),
                            resultSet.getString("custId"),
                            resultSet.getString("type"),
                            resultSet.getString("time"),
                            resultSet.getString("date")
                    )
            );
        }
        return dto;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }




}
