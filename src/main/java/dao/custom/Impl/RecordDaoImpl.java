package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.RecordDao;
import dto.RecordDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordDaoImpl implements RecordDao {
    @Override
    public ArrayList<RecordDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(RecordDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO record VALUES(?,?,?,?)",dto.getPetId(),dto.getRecordId(),dto.getDate(),dto.getDescription());
    }

    @Override
    public boolean update(RecordDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public RecordDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<RecordDto> getRecords(String petId) throws SQLException {
        List<RecordDto> list = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM record WHERE petId = ?",petId);
        while(resultSet.next()){
            list.add(new RecordDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return list;
    }
}
