package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.PetDao;
import db.DbConnection;
import dto.PetDto;
import entity.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetDaoImpl implements PetDao {
    @Override
    public boolean save(Pet entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO pet VALUES(?,?,?,?,?,?,?)",
                entity.getPetId(),
                entity.getName(),
                entity.getAge(),
                entity.getBreed(),
                entity.getGender(),
                entity.getColor(),
                entity.getCustId()

        );
    }

    @Override
    public ArrayList<Pet> getAll() throws SQLException {
        ArrayList<Pet> dto = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM pet");
        while(resultSet.next()){
            dto.add(
                    new Pet(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7)

                    )
            );
        }
        return dto;
    }

    @Override
    public boolean update(Pet dto) throws SQLException {
        return SQLUtil.execute("UPDATE pet SET name = ?, breed = ?, gender = ?, age = ?, custId = ?, color = ? WHERE petId = ?",
                dto.getName(),
                dto.getBreed(),
                dto.getGender(),
                dto.getAge(),
                dto.getCustId(),
                dto.getColor(),
                dto.getPetId()
        );
    }

    @Override
    public Pet search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM pet WHERE petId = ?", id);
        rst.next();
        return new Pet(
                rst.getString(1),
                rst.getString(2),
                rst.getInt(3),
                rst.getString(4),
                rst.getString(5),
                rst.getString(6),
                rst.getString(7)

        );
    }

    @Override
    public boolean delete(String petId) throws SQLException {
        return SQLUtil.execute("DELETE FROM pet WHERE petId = ?",petId);
    }

    @Override
    public String getNextPetId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT petId FROM pet ORDER BY petId DESC LIMIT 1");
        if(resultSet.next()){
            return splitPetId(resultSet.getString(1));
        }else return splitPetId(null);
    }

    @Override
    public String splitPetId(String petId) {
        if(petId!=(null)){
            String [] split = petId.split("P");
            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("P%03d",id);
        }
        else return "P001";
    }
}
