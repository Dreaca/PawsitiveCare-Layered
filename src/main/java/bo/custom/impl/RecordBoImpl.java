package bo.custom.impl;

import bo.custom.RecordBo;
import dao.DaoFactory;
import dao.custom.RecordDao;
import dto.RecordDto;
import entity.Record;

import java.io.FileReader;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordBoImpl implements RecordBo {
    RecordDao dao = (RecordDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.RECORD);
    @Override
    public List<RecordDto> getRecords(String petId) throws SQLException {
        List<Record> records = dao.getRecords(petId);
        ArrayList<RecordDto> list = new ArrayList<>();
        for (Record r: records) {
            list.add(new RecordDto(
                   r.getPetId(),
                    r.getRecordId(),
                    r.getDate(),
                    r.getDescription()
            ));
        }
        return list;
    }

    @Override
    public String getNextRecordId() throws SQLException {
        return dao.getNextRecordId();
    }

    @Override
    public boolean saveRecord(RecordDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Record(dto.getPetId(),dto.getRecordId(),dto.getDescription(),dto.getDate()));
    }
}
