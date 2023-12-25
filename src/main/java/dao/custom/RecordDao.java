package dao.custom;

import dao.CrudDao;
import dao.SuperDao;
import dto.RecordDto;

import java.sql.SQLException;
import java.util.List;

public interface RecordDao extends CrudDao<RecordDto> {
    public List<RecordDto> getRecords(String petId) throws SQLException;
}
