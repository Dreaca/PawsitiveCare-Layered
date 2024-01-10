package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.AppointmentDao;
import dto.AppointmentDto;
import dto.CustomerDto;
import entity.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.DelayQueue;

public class AppointmentDAOImpl implements AppointmentDao {
    @Override
    public boolean save(Appointment entity) throws SQLException {
        System.out.println("check dao save");
        return SQLUtil.execute("INSERT INTO appointment VALUES(?,?,?,?,?,?)",entity.getAppId(),
                entity.getCustId(),
                entity.getType(),
                entity.getTime(),
                entity.getDate(),
                entity.getPrice());
    }

    @Override
    public boolean update(Appointment dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE appointment set custId = ?,type = ?, time = ?, date = ? ,price = ? WHERE appid = ?",
        dto.getCustId(),
                dto.getType(),
                dto.getTime(),
                dto.getDate(),
                dto.getPrice(),
                dto.getAppId());
    }

    @Override
    public Appointment search(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT * FROM appointment WHERE appid = ?",id);
        set.next();
        return new Appointment(
                set.getString(1),
                set.getString(2),
                set.getString(3),
                String.valueOf(set.getTime(4)),
                String.valueOf(set.getDate(5)),
                set.getDouble(6)
        );
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
            String []appId = nextId.split("APP");
            int num = Integer.parseInt(appId[1]);
            num++;
            return String.format("APP%03d",num);
        }
        else {
            return "APP001";
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
                            resultSet.getString("date"),
                            resultSet.getDouble("price")
                    )
            );
        }
        return dto;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM appointment WHERE appId = ?",id);
    }




}
