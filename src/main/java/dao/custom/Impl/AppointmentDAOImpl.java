package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.AppointmentDao;
import dto.AppointmentDto;
import dto.CustomerDto;
import model.CustomerModel;

import java.sql.*;
import java.util.ArrayList;

public class AppointmentDAOImpl implements AppointmentDao {
    @Override
    public boolean save(AppointmentDto dto, CustomerDto cus) throws SQLException {
        CustomerModel model = new CustomerModel();
        String cusID = model.getCustomerId(cus);
        return SQLUtil.execute("INSERT INTO appointment VALUES(?,?,?,?,?)",dto.getAppId(),cusID,dto.getType().toString(),dto.getTime(),dto.getDate());
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
    public ArrayList<AppointmentDto> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM appointment");
        ArrayList<AppointmentDto> dto = new ArrayList<>();
        while (resultSet.next()){
            String custName = CustomerModel.getCustomerName(resultSet.getString("custId"));
            String contact = CustomerModel.getContact(resultSet.getString("custId"));
            dto.add(
                    new AppointmentDto(
                            resultSet.getString("appId"),
                            custName,
                            contact,
                            AppointmentDto.getvalueOf(resultSet.getString("type")),
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

    @Override
    public boolean save(AppointmentDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(AppointmentDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public AppointmentDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
