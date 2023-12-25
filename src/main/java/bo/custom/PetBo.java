package bo.custom;

import dto.PetDto;

import java.sql.SQLException;
import java.util.List;

public interface PetBo extends SuperBO{
    boolean savePet(PetDto dto) throws SQLException, ClassNotFoundException;

    String getNextPetId() throws SQLException;

    List<PetDto> getAllPets() throws SQLException, ClassNotFoundException;

    boolean deletePet(String petId) throws SQLException, ClassNotFoundException;

    boolean updatePet(PetDto dto) throws SQLException, ClassNotFoundException;

    String getCustomerId(String text) throws SQLException;

    String getCustomerName(String ownerId) throws SQLException;
}
