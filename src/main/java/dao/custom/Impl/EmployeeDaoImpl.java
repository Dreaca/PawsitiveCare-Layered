package dao.custom.Impl;

import dao.DaoFactory;
import dao.SQLUtil;
import dao.custom.EmployeeDao;
import dao.custom.LoginDao;
import db.DbConnection;
import dto.EmployeeDto;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDaoImpl implements EmployeeDao {
//    LoginDao loginDao  = (LoginDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.LOGIN);

    @Override
    public ArrayList<EmployeeDto> getAll() throws SQLException {
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
    @Override
    public  boolean  delete(String empId) throws SQLException {
           return SQLUtil.execute("DELETE FROM employee WHERE employeeId = ?",empId);
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
    public boolean save(EmployeeDto dto) throws SQLException {
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
    public boolean update(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public EmployeeDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
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
