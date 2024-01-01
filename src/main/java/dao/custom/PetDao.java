package dao.custom;

import dao.CrudDao;
import dto.PetDto;
import entity.Pet;

import java.sql.SQLException;
import java.util.List;

public interface PetDao extends CrudDao<Pet> {
    String getNextPetId() throws SQLException;
    String splitPetId(String petId);
}
