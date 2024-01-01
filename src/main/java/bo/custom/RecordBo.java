package bo.custom;

import dao.SQLUtil;
import dao.SuperDao;
import dto.RecordDto;

import java.sql.SQLException;
import java.util.List;

public interface RecordBo extends SuperBO {
    List<RecordDto> getRecords(String petId) throws SQLException;

    String getNextRecordId()throws SQLException;

    boolean saveRecord(RecordDto dto) throws SQLException, ClassNotFoundException;
}
