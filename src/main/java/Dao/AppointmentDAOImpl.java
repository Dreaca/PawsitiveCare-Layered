package Dao;

import Db.DbConnection;
import Dto.AppointmentDto;
import Dto.CustomerDto;
import model.CustomerModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Db.DbConnection.getInstance;

public class AppointmentDAOImpl implements AppointmentDao {
    @Override
    public boolean addAppointment(AppointmentDto dto, CustomerDto cus) throws SQLException {
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
    public List<AppointmentDto> getAllAppointments() throws SQLException {
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
}
