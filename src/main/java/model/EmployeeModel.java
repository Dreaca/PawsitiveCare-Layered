package model;

import Db.DbConnection;
import Dto.EmployeeDto;
import javafx.scene.image.Image;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    private String name;
    private String address;
    private String contact;

    private double salary;
    private  String userId;
    private static Image photo;

    public EmployeeModel() {
    }

    public static List<EmployeeDto> getEmployeeDtos() throws SQLException {



        String sql = "SELECT * FROM employee";

        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();

        while (resultSet.next()){
            employeeDtos.add(
             new EmployeeDto(
                    resultSet.getString("employeeId"),
                    resultSet.getString("address"),
                    resultSet.getString("name"),
                    resultSet.getString("contact"),
                    resultSet.getDouble("salary"),
                    resultSet.getString("userId"),
                     resultSet.getString("NIC")
             )
            );

        }
        return employeeDtos;
    }

    public static String generateNextEmpId() throws SQLException {
        String sql = "SELECT * FROM employee ORDER BY employeeID DESC LIMIT 1 ";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return splitEmpID(resultSet.getString(1));
        }else {
            return splitEmpID(null);
        }
    }

    private static String splitEmpID(String empId) {
        if( empId != null ){
            String [] id = empId.split("E");
            int num = Integer.parseInt(id[1]);
            num++;
            return "E00"+num;
        }else {
            return "E001";
        }
    }

    public static boolean  deleteEmployee(String empId) throws SQLException {
        boolean flag = false;
        Connection connection = DbConnection.getInstance().getConnection();;
        try {
            connection.setAutoCommit(false);

            String userId = EmployeeModel.getEmployee(empId);
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM employee WHERE employeeId = ?");
            pstm.setString(1,empId);
            int i = pstm.executeUpdate();
            if(i > 0){
                boolean b = LoginModel.deleteUser(userId);
                if (b) {
                    flag = b;
                    connection.commit();
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        }
        finally {
            connection.setAutoCommit(true);
        }
        return flag;
    }

    private static String getEmployee(String empId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT userId FROM employee WHERE employeeId = ?");
        pstm.setString(1,empId);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString("userId");
        }else {
            return null;
        }
    }

    public static EmployeeDto getEmployeeDetails(String userId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM employee WHERE userId = ?");
        pstm.setString(1,userId);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        else return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean saveEmployee(EmployeeDto dto) throws SQLException {
        String sql = "INSERT INTO employee VALUES(?,?,?,?,?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getEmpId());
        pstm.setString(2,dto.getName());
        pstm.setString(3,dto.getAddress());
        pstm.setString(4,dto.getContact());
        pstm.setDouble(5,dto.getSalary());
        pstm.setString(6,dto.getUserId());
        pstm.setBlob(7, (Blob) null);
        pstm.setString(8,dto.getNIC());

        int i = pstm.executeUpdate();
        return i > 0;
    }

    public boolean updateName(String name, String userId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE employee SET name = ? WHERE userId = ?");
        pstm.setString(1,name);
        pstm.setString(2,userId);
        int i = pstm.executeUpdate();
        return i > 0;
    }

    public boolean UpdateNIC(String userId, String nic) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE employee SET NIC = ? WHERE userId = ?");
        pstm.setString(1,nic);
        pstm.setString(2,userId);
        int i = pstm.executeUpdate();
        return i>0;
    }
}
