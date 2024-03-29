package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.LoginDao;
import dto.LoginFormDto;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDaoImpl implements LoginDao {
   // private EmployeeDao dao = (EmployeeDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.EMPLOYEE);
    @Override
    public  String generateNExtUserID() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user ORDER BY userId DESC LIMIT 1");
        if(resultSet.next()){
            return splitUserID(resultSet.getString(1));
        }else {
            return splitUserID(null);
        }
    }
    @Override
    public String splitUserID(String userId) {
        if(userId != null && !userId.equals("Admin")){
            String [] newUser = userId.split("U");
            int num = Integer.parseInt(newUser[1]);
            num++;
            return String.format("U%03d",num);
        }else {
            return "U001";
        }
    }

    @Override
    public boolean authenticate(LoginFormDto login) throws SQLException {
        String usedUserName = login.getUserName();
        String usedPassword = login.getPassword();
        boolean isValid = false;
        ResultSet resultSet = SQLUtil.execute("SELECT passWord FROM user WHERE userName = ?",usedUserName);
        if(resultSet.next()){
            String pw = resultSet.getString("password");
            if (usedPassword.equals(pw)) {
                isValid = true;
            }
            else isValid = false;
        }
        return isValid;
    }

    @Override
    public String getUser(LoginFormDto login) throws SQLException {
        String usedUserName = login.getUserName();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user WHERE userName = ?",usedUserName);
        if (resultSet.next()){
            if (usedUserName.equals(resultSet.getString("userName"))){
                return resultSet.getString("userId");
            }
        }

        return null;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String userId) throws SQLException {
        return SQLUtil.execute("DELETE FROM user WHERE userId = ?",userId);
    }

    @Override
    public boolean setUpdateUserId(String oldUserId, String newUserId) throws SQLException {
        return SQLUtil.execute("UPDATE user SET userId = ? WHERE userName = ?",newUserId,oldUserId);
    }

    @Override
    public User getUser(String userName) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user WHERE userName = ?",userName);
        if (resultSet.next()) {
            return new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        }
        else return null;
    }

    @Override
    public boolean save(User en) throws SQLException {
        return SQLUtil.execute("INSERT INTO user VALUES (?,?,?)",
                en.getUserId(),
                en.getUserName(),
                en.getPassWord()
        );
    }

    @Override
    public boolean update(User user ) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateUserName(String userName, String userIdText) throws SQLException {
        return SQLUtil.execute("UPDATE user SET userName = ? WHERE userId = ? ",userName,userIdText);
    }

    @Override
    public boolean checkPass(String userId, String oldPasswordText) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT password FROM user WHERE userId = ? ",userId);
        if (resultSet.next()){
            return oldPasswordText == resultSet.getString(1);
        }
        return false;
    }

    @Override
    public boolean updatePassword(String userId, String newpW) throws SQLException {
        return SQLUtil.execute("UPDATE user SET password = ? WHERE userId = ?",newpW,userId);
    }

    @Override
    public String getNic(String nic) throws SQLException {
            ResultSet resultSet = SQLUtil.execute("SELECT userId FROM employee WHERE NIC LIKE ?",("%"+nic));
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            else return null;

    }
}
