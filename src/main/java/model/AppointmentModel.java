package model;

import Db.DbConnection;
import Dto.AppointmentDto;
import Dto.CustomerDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Db.DbConnection.getInstance;

public class AppointmentModel {
    public boolean addAppointment(AppointmentDto dto, CustomerDto cus) throws SQLException {
        CustomerModel model = new CustomerModel();
        Connection connection = getInstance().getConnection();
        String cusID = model.getCustomerId(cus);

        PreparedStatement pstm = connection.prepareStatement("INSERT INTO appointment VALUES(?,?,?,?,?)");
        pstm.setString(1,dto.getAppId());
        pstm.setString(2,cusID);
        pstm.setString(3,dto.getType().toString());
        pstm.setString(4, dto.getTime());
        pstm.setDate(5, Date.valueOf(dto.getDate()));
        int i = pstm.executeUpdate();
        return i > 0;
    }

    public String getNextAppid() throws SQLException {
        Connection connection = getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT appId FROM appointment ORDER BY appId DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return splitAppId(resultSet.getString(1));
        }
        return splitAppId(null);
    }

    private String splitAppId(String nextId) {
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

    public List<AppointmentDto> getAllAppointments() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM appointment");
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<AppointmentDto> dto = new ArrayList<>();
        while (resultSet.next()){
            String custName = CustomerModel.getCustomerName(resultSet.getString("custId"));
            String contact = ContactModel.getContact(resultSet.getString("custId"));
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
