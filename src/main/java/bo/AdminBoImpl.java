package bo;

import dao.DaoFactory;
import dao.custom.LoginDao;
import dto.AdminDto;

import java.sql.SQLException;

public class AdminBoImpl implements AdminBo{
    private LoginDao loginDao = (LoginDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.LOGIN);
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
    public AdminDto getUser(String user) throws SQLException {
        return loginDao.getUser(user);
    }
}
