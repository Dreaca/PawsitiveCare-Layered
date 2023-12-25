package bo;

import dao.custom.EmployeeDao;
import dao.custom.Impl.EmployeeDaoImpl;
import dao.custom.Impl.LoginDaoImpl;
import dao.custom.LoginDao;
import dto.EmployeeDto;
import dto.LoginFormDto;

import java.sql.SQLException;

public class AddNewEmployeeFormBoImpl implements AddNewEmployeeFormBO{
    private EmployeeDao dao = new EmployeeDaoImpl();
    private LoginDao loginDao = new LoginDaoImpl();
    @Override
    public boolean saveUser(LoginFormDto dto) throws SQLException, ClassNotFoundException {
       return loginDao.save(dto);
    }

    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(dto);
    }
}
