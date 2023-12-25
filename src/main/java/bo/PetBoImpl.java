package bo;

import dao.DaoFactory;
import dao.custom.Impl.PetDaoImpl;
import dao.custom.PetDao;
import dto.PetDto;

import java.sql.SQLException;

public class PetBoImpl implements PetBo{
    private PetDao petDao = (PetDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.PET);
    @Override
    public boolean savePet(PetDto dto) throws SQLException, ClassNotFoundException {
        return petDao.save(dto);
    }

    @Override
    public String getNextPetId() throws SQLException {
       return petDao.getNextPetId();
    }
}
