package bo;

import dto.EmployeeDto;
import dto.LoginFormDto;
import model.EmployeeModel;

import java.sql.SQLException;

public interface AddNewEmployeeFormBO extends SuperBO{
    boolean saveUser(LoginFormDto dto) throws SQLException, ClassNotFoundException;
    boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;
}
