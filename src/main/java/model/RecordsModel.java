package model;

import db.DbConnection;
import dto.RecordDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordsModel {
    public static String getNextRecordId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT recordId FROM record ORDER BY recordId DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return generateNextRecId(resultSet.getString(1));
        }else return "R001";
    }

    private static String generateNextRecId(String recId) {
        if(!recId.isEmpty()){
            String [] rec = recId.split("R");
            int id = Integer.parseInt(rec[1]);
            id++;
            return String.format("R%03d",id);
        }
        else return "R001";
    }

    public List<RecordDto> getRecords(String petId) throws SQLException {
        List<RecordDto> list = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM record WHERE petId = ?");
        pstm.setString(1,petId);
        ResultSet resultSet = pstm.executeQuery();
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

    public boolean saveRecord(RecordDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO record VALUES(?,?,?,?)");
        pstm.setString(1,dto.getPetId());
        pstm.setString(2,dto.getRecordId());
        pstm.setString(3, dto.getDate());
        pstm.setString(4,dto.getDescription());
        int i = pstm.executeUpdate();
        return i>0;
    }
}
