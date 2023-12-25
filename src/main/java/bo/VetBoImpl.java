package bo;

import dao.DaoFactory;
import dao.custom.VetDao;
import dto.VetDto;

import java.sql.SQLException;
import java.util.List;

public class VetBoImpl implements VetBo{
    private VetDao dao = (VetDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.VET);
    @Override
    public VetDto searchVet(String id) throws SQLException, ClassNotFoundException {
        return dao.search(id);
    }

    @Override
    public boolean deleteVet(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public boolean saveVeterinarian(VetDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(dto);
    }

    @Override
    public List<VetDto> loadAllVets() throws SQLException, ClassNotFoundException {
        return dao.getAll();
    }
}
