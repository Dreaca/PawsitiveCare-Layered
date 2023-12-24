package Dao;

import Db.DbConnection;
import Dto.EmployeeDto;
import model.EmployeeModel;
import model.LoginModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{

    @Override
    public List<EmployeeDto> getEmployeeDtos() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");
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

    @Override
    public String generateNextEmpId() throws SQLException {
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
    public String splitEmpID(String empId) {
        if( empId != null ){
            String [] id = empId.split("E");
            int num = Integer.parseInt(id[1]);
            num++;
            return String.format("E%03d",num);
        }else {
            return "E001";
        }
    }
    public  boolean  deleteEmployee(String empId) throws SQLException {
        boolean flag = false;
        Connection connection = DbConnection.getInstance().getConnection();;
        try {
            connection.setAutoCommit(false);

            String userId = getEmployee(empId);
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

    @Override
    public String getEmployee(String empId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT userId FROM employee WHERE employeeId = ?",empId);
        if (resultSet.next()){
            return resultSet.getString("userId");
        }else {
            return null;
        }
    }
    @Override
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

    @Override
    public boolean updateName(String name, String userId) throws SQLException {
        return SQLUtil.execute("UPDATE employee SET name = ? WHERE userId = ?",name,userId);
    }

    @Override
    public boolean UpdateNIC(String userId, String nic) throws SQLException {
        return SQLUtil.execute("UPDATE employee SET NIC = ? WHERE userId = ?",nic,userId);
    }
}
