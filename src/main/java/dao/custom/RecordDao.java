package dao.custom;

import dao.CrudDao;
import entity.Record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RecordDao extends CrudDao<Record> {
    public List<Record> getRecords(String petId) throws SQLException;
    public  String getNextRecordId() throws SQLException ;

    String generateNextRecId(String recId);
}
