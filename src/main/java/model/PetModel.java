package model;

import Db.DbConnection;
import Dto.PetDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class PetModel {


    public static boolean savePet(PetDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO pet VALUES(?,?,?,?,?,?)");
        pstm.setString(1,dto.getPetId());
        pstm.setString(2, dto.getPetName());
        pstm.setString(3,dto.getPetBreed());
        pstm.setString(4,dto.getPetGender());
        pstm.setString(5,dto.getOwnerId());
        pstm.setString(6,dto.getColor());
        int i = pstm.executeUpdate();
        return i > 0;
    }

    public static List<PetDto> getAllPets() throws SQLException {
        ArrayList<PetDto> dto = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM pet");
        ResultSet resultSet = pstm.executeQuery();
        while(resultSet.next()){
            dto.add(
              new PetDto(
                      resultSet.getString(1),
                      resultSet.getString(2),
                      resultSet.getString(3),
                      resultSet.getString(4),
                      resultSet.getString(5),
                      resultSet.getString(6)

              )
            );
        }
        return dto;
    }

    public boolean updatePet(PetDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE pet SET name = ?, breed = ?, gender = ?, custId = ?, color = ? WHERE petId = ?");
        pstm.setString(1, dto.getPetName());
        pstm.setString(2,dto.getPetBreed());
        pstm.setString(3,dto.getPetGender());
        pstm.setString(4, dto.getOwnerId());
        pstm.setString(5,dto.getColor());
        pstm.setString(6,dto.getPetId());
        int i = pstm.executeUpdate();
        return i > 0;
    }

    public boolean deletePet(String petId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM pet WHERE petId = ?");
        pstm.setString(1,petId);
        int i = pstm.executeUpdate();
        return i > 0;
    }

    public String getNextPetId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT petId FROM pet ORDER BY petId DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return splitPetId(resultSet.getString(1));
        }else return null;
    }

    private String splitPetId(String petId) {
        String [] split = petId.split("P");
        if(petId!=null){
        int id = Integer.parseInt(split[1]);
        id++;
        return "P00"+id;
        }
        else return "P001";
    }

    /*public static String[] getAllPetId() throws SQLException {
        List<PetDto> dto = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT petId FROM pet");
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            dto.add(
                    new PetDto(
                    resultSet.getString(1)
                    )
            );

        }
        return dtoArray(dto);
    }

    private static String[] dtoArray(List<PetDto> dto) {
        String [] strings = new String[dto.size()];
        for (int i = 0; i < dto.size(); i++) {
            strings[i] = dto.get(i).getPetId();
        }
        return strings;
    }*/
}
