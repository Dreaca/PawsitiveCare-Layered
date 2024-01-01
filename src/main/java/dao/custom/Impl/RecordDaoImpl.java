package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.RecordDao;
import entity.Record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordDaoImpl implements RecordDao {
    @Override
    public ArrayList<Record> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(Record entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO record VALUES(?,?,?,?)",entity.getPetId(),entity.getRecordId(),entity.getDate(),entity.getDescription());
    }

    @Override
    public boolean update(Record dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Record search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Record> getRecords(String petId) throws SQLException {
        List<Record> list = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM record WHERE petId = ?",petId);
        while(resultSet.next()){
            list.add(new Record(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4).toLocalDate()
            ));
        }
        return list;
    }

    @Override
    public String getNextRecordId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT recordId FROM record ORDER BY recordId DESC LIMIT 1");
        if (resultSet.next()){
            return generateNextRecId(resultSet.getString(1));
        }else return generateNextRecId(null);
    }

    @Override
    public String generateNextRecId(String recId) {
        if(!recId.isEmpty()){
            String [] rec = recId.split("R");
            int id = Integer.parseInt(rec[1]);
            id++;
            return String.format("R%03d",id);
        }
        else return "R001";
    }
}
