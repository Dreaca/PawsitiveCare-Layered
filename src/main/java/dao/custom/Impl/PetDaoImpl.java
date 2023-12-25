package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.PetDao;
import db.DbConnection;
import dto.PetDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetDaoImpl implements PetDao {
    @Override
    public boolean save(PetDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO pet VALUES(?,?,?,?,?,?)",dto.getPetId(),
                dto.getPetName(),
                dto.getPetBreed(),
                dto.getPetGender(),
                dto.getOwnerId(),
                dto.getColor()
        );
    }

    @Override
    public ArrayList<PetDto> getAll() throws SQLException {
        ArrayList<PetDto> dto = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM pet");
        while(resultSet.next()){
            dto.add(
                    new PetDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)

                    )
            );
        }
        return dto;
    }

    @Override
    public boolean update(PetDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE pet SET name = ?, breed = ?, gender = ?, custId = ?, color = ? WHERE petId = ?",dto.getPetName(),
                dto.getPetBreed(),
                dto.getPetGender(),
                dto.getOwnerId(),
                dto.getColor(),
                dto.getPetId()
        );
    }

    @Override
    public PetDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String petId) throws SQLException {
        return SQLUtil.execute("DELETE FROM pet WHERE petId = ?",petId);
    }

    @Override
    public String getNextPetId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT petId FROM pet ORDER BY petId DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return splitPetId(resultSet.getString(1));
        }else return null;
    }

    @Override
    public String splitPetId(String petId) {
        String [] split = petId.split("P");
        if(petId!=null){
            int id = Integer.parseInt(split[1]);
            id++;
            return "P00"+id;
        }
        else return "P001";
    }
}
