package bo;

import dto.PetDto;

import java.sql.SQLException;

public interface PetBo extends SuperBO{
    boolean savePet(PetDto dto) throws SQLException, ClassNotFoundException;

    String getNextPetId() throws SQLException;
}
