package bo;

import dto.EmployeeDto;
import dto.LoginFormDto;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBo extends SuperBO{
    boolean saveUser(LoginFormDto dto) throws SQLException, ClassNotFoundException;
    boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;

    boolean UpdateNIC(String userId, String nic) throws SQLException;

    boolean updateName(String name, String userId) throws SQLException;

    boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException;

    List<EmployeeDto> getEmployees() throws SQLException, ClassNotFoundException;

    boolean UpdateUserId(String oldUserName, String newUserId) throws SQLException;

    String generateNextEmpId() throws SQLException;

    String generateNExtUserID() throws SQLException;
}
