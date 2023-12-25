package dao.custom.Impl;

import dao.custom.VetDao;
import db.DbConnection;
import dto.VetDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VetDaoImpl implements VetDao {
    @Override
    public boolean save(VetDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO veterinarian VALUES(?,?,?,?,?)");
        pstm.setString(1,dto.getVetId());
        pstm.setString(2, dto.getVetName());
        pstm.setString(3, "E001");
        pstm.setString(4,dto.getContact());
        pstm.setString(5,dto.getEmail());
        int i = pstm.executeUpdate();
        return i > 0;
    }

    @Override
    public boolean update(VetDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<VetDto> getAll() throws SQLException {
        ArrayList<VetDto> dto = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM veterinarian");
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            dto.add(
                    new VetDto(
                            resultSet.getString("vetId"),
                            resultSet.getString("name"),
                            resultSet.getString("contact"),
                            resultSet.getString("email")
                    )
            );
        }
        return dto;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM veterinarian WHERE vetId = ?");
        pstm.setString(1,id);
        int i = pstm.executeUpdate();
        return i>0;
    }

    @Override
    public VetDto search(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM veterinarian Where vetId = ?");
        pstm.setString(1,id);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return new VetDto(
                    resultSet.getString("vetId"),
                    resultSet.getString("name"),
                    resultSet.getString("contact"),
                    resultSet.getString("email")
            );
        }
        else return null;
    }

    @Override
    public String getVetName(String vetId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT name FROM veterinarian WHERE vetId = ?");
        pstm.setString(1,vetId);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        else {
            return null;
        }
    }

    @Override
    public String getVetId(String vetName) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT vetId FROM veterinarian WHERE name = ?");
        pstm.setString(1,vetName);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        else {
            return null;
        }
    }
}
