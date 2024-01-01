package dao.custom;

import dao.CrudDao;
import dto.VetDto;
import entity.Veterinarian;

import java.sql.SQLException;
import java.util.List;

public interface VetDao extends CrudDao<Veterinarian> {
    String getVetName(String vetId) throws SQLException;

    String getVetId(String vetName) throws SQLException;
}
