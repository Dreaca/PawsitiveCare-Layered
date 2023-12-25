package bo.custom;

import dto.AdminDto;
import dto.LoginFormDto;

import java.sql.SQLException;

public interface LoginBo extends SuperBO{
    boolean updateUserName(String userName, String userIdText) throws SQLException;

    boolean validatePasswords(String userId, String oldPasswordText) throws SQLException;

    boolean updatePassword(String userId, String newpW) throws SQLException;

    AdminDto getUser(String user) throws SQLException, ClassNotFoundException;

    boolean authenticateUser(LoginFormDto loginFormDto) throws SQLException;

    String getUserDetail(LoginFormDto loginFormDto) throws SQLException;

    String generateNExtUserID() throws SQLException;
}
