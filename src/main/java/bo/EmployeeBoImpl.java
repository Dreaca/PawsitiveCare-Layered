package bo;

import dao.DaoFactory;
import dao.custom.EmployeeDao;
import dao.custom.Impl.EmployeeDaoImpl;
import dao.custom.Impl.LoginDaoImpl;
import dao.custom.LoginDao;
import dto.EmployeeDto;
import dto.LoginFormDto;

import java.sql.SQLException;
import java.util.List;

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

    @Override
    public boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException {
        return dao.delete(empId);
    }

    @Override
    public List<EmployeeDto> getEmployees() throws SQLException, ClassNotFoundException {
        return dao.getAll();
    }

    @Override
    public boolean UpdateUserId(String oldUserName, String newUserId) throws SQLException {
        return dao.updateName(oldUserName,newUserId);
    }

    @Override
    public String generateNextEmpId() throws SQLException {
        return dao.generateNextEmpId();
    }

    @Override
    public String generateNExtUserID() throws SQLException {
       return loginDao.generateNExtUserID();
    }
}
