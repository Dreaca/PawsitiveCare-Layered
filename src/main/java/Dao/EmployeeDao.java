package Dao;

import Dto.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
     List<EmployeeDto> getEmployeeDtos() throws SQLException;
     String generateNextEmpId() throws SQLException;
     String splitEmpID(String empId);
    boolean  deleteEmployee(String empId) throws SQLException;
    String getEmployee(String empId) throws SQLException;
    boolean saveEmployee(EmployeeDto dto) throws SQLException;
    boolean updateName(String name, String userId) throws SQLException;
    boolean UpdateNIC(String userId, String nic) throws SQLException;
}
