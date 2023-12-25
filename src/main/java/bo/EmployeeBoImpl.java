package bo;

import dao.DaoFactory;
import dao.custom.EmployeeDao;
import dao.custom.Impl.EmployeeDaoImpl;
import dao.custom.Impl.LoginDaoImpl;
import dao.custom.LoginDao;
import dto.EmployeeDto;
import dto.LoginFormDto;

import java.sql.SQLException;

public class EmployeeBoImpl implements EmployeeBo {
private EmployeeDao dao = (EmployeeDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.EMPLOYEE);
    private LoginDao loginDao = (LoginDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.LOGIN);
    @Override
    public boolean saveUser(LoginFormDto dto) throws SQLException, ClassNotFoundException {
       return loginDao.save(dto);
    }

    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(dto);
    }

    @Override
    public boolean UpdateNIC(String userId, String nic) throws SQLException {
        return dao.UpdateNIC(userId,nic);
    }

    @Override
    public boolean updateName(String name, String userId) throws SQLException {
        return dao.updateName(name,userId);
    }
}
