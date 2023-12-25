package dao.custom;

import dao.CrudDao;
import dto.VetDto;

import java.sql.SQLException;
import java.util.List;

public interface VetDao extends CrudDao<VetDto> {
    String getVetName(String vetId) throws SQLException;
    List<String> getAllVetNames() throws SQLException;
    String getVetId(String vetName) throws SQLException;
}
