package bo.custom.impl;

import bo.custom.LoginBo;
import dao.DaoFactory;
import dao.custom.EmployeeDao;
import dao.custom.Impl.EmployeeDaoImpl;
import dao.custom.LoginDao;
import dto.AdminDto;
import dto.EmployeeDto;
import dto.LoginFormDto;
import entity.Employee;

import java.sql.SQLException;

public class LoginBoImpl implements LoginBo {
    private LoginDao loginDao = (LoginDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.LOGIN);
    private EmployeeDao eDao = (EmployeeDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.EMPLOYEE);
    @Override
    public boolean updateUserName(String userName, String userIdText) throws SQLException {
        return loginDao.updateUserName(userName,userIdText);
    }

    @Override
    public boolean validatePasswords(String userId, String oldPasswordText) throws SQLException {
        return loginDao.checkPass(userId,oldPasswordText);
    }

    @Override
    public boolean updatePassword(String userId, String newpW) throws SQLException {
        return loginDao.updatePassword(userId,newpW);
    }

    @Override
    public AdminDto getUser(String user) throws SQLException, ClassNotFoundException {
        LoginFormDto user1 = loginDao.getUser(user);
        Employee search = eDao.search(user1.getUserID());
        EmployeeDto employeeDto = new EmployeeDto(search.getEmployeeId(), search.getName(), search.getAddress(), search.getContact(), search.getSalary(), search.getUserId(), search.getNIC());
        return new AdminDto(employeeDto, user1);
    }

    @Override
    public boolean authenticateUser(LoginFormDto loginFormDto) throws SQLException {
        return loginDao.authenticate(loginFormDto);
    }

    @Override
    public String getUserDetail(LoginFormDto loginFormDto) throws SQLException {
        return loginDao.getUser(loginFormDto);
    }

    @Override
    public String generateNExtUserID() throws SQLException {
        return loginDao.generateNExtUserID();
    }
}
