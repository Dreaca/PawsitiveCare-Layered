package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.VetDao;
import db.DbConnection;
import dto.VetDto;
import entity.Veterinarian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VetDaoImpl implements VetDao {
    @Override
    public boolean save(Veterinarian dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO veterinarian VALUES(?,?,?,?,?)",
                dto.getVetId(),
                dto.getName(),
                dto.getUserId(),
                dto.getContact(),
                dto.getEmail());
    }

    @Override
    public boolean update(Veterinarian dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Veterinarian> getAll() throws SQLException {
        ArrayList<Veterinarian> dto = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM veterinarian");
        while (resultSet.next()){
            dto.add(
                    new Veterinarian(
                            resultSet.getString("vetId"),
                            resultSet.getString("name"),
                            null,
                            resultSet.getString("contact"),
                            resultSet.getString("email")
                    )
            );
        }
        return dto;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM veterinarian WHERE vetId = ?",id);
    }

    @Override
    public Veterinarian search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM veterinarian Where vetId = ?",id);
        if (resultSet.next()){
            return new Veterinarian(
                    resultSet.getString("vetId"),
                    resultSet.getString("name"),
                    resultSet.getString("userId"),
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
