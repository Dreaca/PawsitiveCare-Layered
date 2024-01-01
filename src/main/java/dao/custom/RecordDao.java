package dao.custom;

import dao.CrudDao;
import dao.SuperDao;
import dto.RecordDto;
import entity.Record;

import java.sql.SQLException;
import java.util.List;

public interface RecordDao extends CrudDao<Record> {
    public List<RecordDto> getRecords(String petId) throws SQLException;
}
