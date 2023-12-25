package bo;

import dto.AdminDto;

import java.sql.SQLException;

public interface AdminBo extends SuperBO{
    boolean updateUserName(String userName, String userIdText) throws SQLException;

    boolean validatePasswords(String userId, String oldPasswordText) throws SQLException;

    boolean updatePassword(String userId, String newpW) throws SQLException;

    AdminDto getUser(String user) throws SQLException;

}
