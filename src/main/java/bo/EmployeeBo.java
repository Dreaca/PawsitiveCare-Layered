package bo;

import dto.EmployeeDto;
import dto.LoginFormDto;

import java.sql.SQLException;

public interface EmployeeBo extends SuperBO{
    boolean saveUser(LoginFormDto dto) throws SQLException, ClassNotFoundException;
    boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;

    boolean UpdateNIC(String userId, String nic) throws SQLException;

    boolean updateName(String name, String userId) throws SQLException;
}
