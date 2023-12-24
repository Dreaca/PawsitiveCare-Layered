package Dao;

import Dto.AdminDto;
import Dto.LoginFormDto;

import java.sql.SQLException;

public interface LoginDao {
     String generateNExtUserID() throws SQLException;
    String splitUserID(String userId);
    boolean authenticate(LoginFormDto login) throws SQLException;
    String getUser(LoginFormDto login) throws SQLException;
    boolean deleteUser(String userId) throws SQLException;
    boolean setUpdateUserId(String oldUserId, String newUserId) throws SQLException;
    AdminDto getUser(String userName) throws SQLException;
    boolean saveUser(LoginFormDto LDto) throws SQLException;
    boolean updateUserName(String userName, String userIdText) throws SQLException;
    boolean checkPass(String userId, String oldPasswordText) throws SQLException;
    boolean updatePassword(String userId, String newpW) throws SQLException;
}
