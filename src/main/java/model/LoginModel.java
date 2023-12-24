package model;

import Db.DbConnection;
import Dto.AdminDto;
import Dto.EmployeeDto;
import Dto.LoginFormDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    public LoginModel() {
    }

    public static String generateNExtUserID() throws SQLException {
        String sql = "SELECT * FROM user ORDER BY userId DESC LIMIT 1";
        Connection connection =DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return splitUserID(resultSet.getString(1));
        }else {
            return splitUserID(null);
        }
    }

    private static String splitUserID(String userId) {
        if(userId != null){
            String [] newUser = userId.split("E");
            int num = Integer.parseInt(newUser[1]);
            num++;
            return "E00"+num;
        }else {
            return "E001";
        }
    }



    public static boolean authenticate(LoginFormDto login) throws SQLException {
        String usedUserName = login.getUserName();
        String usedPassword = login.getPassword();
        boolean isValid = false;

        String sql = "SELECT passWord FROM user WHERE userName = ?";

       Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,usedUserName);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            String pw = resultSet.getString("password");
            if (usedPassword.equals(pw)) {
                isValid = true;
            }
            else isValid = false;
        }
        return isValid;
    }

    public static String getUser(LoginFormDto login) throws SQLException {
       String usedUserName = login.getUserName();

        String sql = "SELECT * FROM user WHERE userName = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,usedUserName);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            if (usedUserName.equals(resultSet.getString("userName"))){
                return resultSet.getString("userId");
            }
        }

        return null;
    }

    public static boolean deleteUser(String userId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM user WHERE userId = ?");
        pstm.setString(1,userId);
        int i = pstm.executeUpdate();
        return i > 0;
    }

    public static boolean setUpdateUserId(String oldUserId, String newUserId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE user SET userId = ? WHERE userName = ?");
        pstm.setString(1,newUserId);
        pstm.setString(2,oldUserId);
        int i = pstm.executeUpdate();
        return i>0;
    }

    public static AdminDto getUser(String userName) throws SQLException {
        LoginFormDto loginFormDto = new LoginFormDto();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM user WHERE userName = ?");
        pstm.setString(1, userName);
        ResultSet resultSet = pstm.executeQuery();
        EmployeeDto dto = null;
        if (resultSet.next()) {
            dto = EmployeeModel.getEmployeeDetails(resultSet.getString(1));
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

    public boolean saveUser(LoginFormDto LDto) throws SQLException {

        String sql = "INSERT INTO user VALUES (?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,LDto.getUserID());
        pstm.setString(2,LDto.getUserName());
        pstm.setString(3,LDto.getPassword());
        int i = pstm.executeUpdate();

        return i > 0;
    }

    public boolean updateUserName(String userName, String userIdText) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE user SET userName = ? WHERE userId = ? ");
        pstm.setString(1,userName);
        pstm.setString(2,userIdText);
        int i = pstm.executeUpdate();
        return i > 0;
    }

    public boolean checkPass(String userId, String oldPasswordText) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT password FROM user WHERE userId = ? ");
        pstm.setString(1,userId);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
           return oldPasswordText == resultSet.getString(1);
        }
        return false;
    }

    public boolean updatePassword(String userId, String newpW) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE user SET password = ? WHERE userId = ?");
        pstm.setString(1,newpW);
        pstm.setString(2,userId);
        int i = pstm.executeUpdate();
        return i > 0;
    }
}
