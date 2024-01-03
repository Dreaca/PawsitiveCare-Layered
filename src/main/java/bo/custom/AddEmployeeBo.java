package bo.custom;

import dto.EmployeeDto;
import dto.LoginFormDto;

import java.sql.SQLException;

public interface AddEmployeeBo extends SuperBO{
    boolean saveData(LoginFormDto lDto, EmployeeDto dto) throws SQLException, ClassNotFoundException;
}
