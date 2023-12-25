package dao.custom.Impl;

import dao.DaoFactory;
import dao.SQLUtil;
import dao.custom.EmployeeDao;
import dao.custom.LoginDao;
import db.DbConnection;
import dto.AdminDto;
import dto.EmployeeDto;
import dto.LoginFormDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDaoImpl implements LoginDao {
    EmployeeDao dao = (EmployeeDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.EMPLOYEE);
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
        if(userId != null){
            String [] newUser = userId.split("E");
            int num = Integer.parseInt(newUser[1]);
            num++;
            return "E00"+num;
        }else {
            return "E001";
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
    public ArrayList<LoginFormDto> getAll() throws SQLException, ClassNotFoundException {
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
    public AdminDto getUser(String userName) throws SQLException {
        LoginFormDto loginFormDto = new LoginFormDto();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM user WHERE userName = ?");
        pstm.setString(1, userName);
        ResultSet resultSet = pstm.executeQuery();
        EmployeeDto dto = null;
        if (resultSet.next()) {
            try {
                dto = dao.search(resultSet.getString(1));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println(dto + "Employee ");
            loginFormDto = new LoginFormDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
            System.out.println(loginFormDto+"login");
        }
        return new AdminDto(dto, loginFormDto);
    }

    @Override
    public boolean save(LoginFormDto LDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO user VALUES (?,?,?)",LDto.getUserID(),
                LDto.getUserName(),
                LDto.getPassword()
        );
    }

    @Override
    public boolean update(LoginFormDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public LoginFormDto search(String id) throws SQLException, ClassNotFoundException {
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
}
