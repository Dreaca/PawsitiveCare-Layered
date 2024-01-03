package bo.custom;

import dto.EmployeeDto;

import java.sql.SQLException;

public interface UpdateEmployeeBo extends SuperBO{
    boolean updateData(String userID, String pass, EmployeeDto dto) throws SQLException, ClassNotFoundException;
}
