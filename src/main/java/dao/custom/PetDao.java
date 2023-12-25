package dao.custom;

import dao.CrudDao;
import dto.PetDto;

import java.sql.SQLException;
import java.util.List;

public interface PetDao extends CrudDao<PetDto> {
    String getNextPetId() throws SQLException;
    String splitPetId(String petId);
}
