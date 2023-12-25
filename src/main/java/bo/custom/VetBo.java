package bo.custom;

import dto.VetDto;

import java.sql.SQLException;
import java.util.List;

public interface VetBo extends SuperBO{
    VetDto searchVet(String id) throws SQLException, ClassNotFoundException;

    boolean deleteVet(String id) throws SQLException, ClassNotFoundException;

    boolean saveVeterinarian(VetDto dto) throws SQLException, ClassNotFoundException;

    List<VetDto> loadAllVets() throws SQLException, ClassNotFoundException;
}
