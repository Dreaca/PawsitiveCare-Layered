package bo.custom.impl;

import bo.custom.PetBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dao.custom.PetDao;
import dto.PetDto;

import java.sql.SQLException;
import java.util.List;

public class PetBoImpl implements PetBo {
    private PetDao petDao = (PetDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.PET);
    private CustomerDao customerDao = (CustomerDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.CUSTOMER);

    @Override
    public boolean savePet(PetDto dto) throws SQLException, ClassNotFoundException {
        return petDao.save(dto);
    }

    @Override
    public String getNextPetId() throws SQLException {
       return petDao.getNextPetId();
    }

    @Override
    public List<PetDto> getAllPets() throws SQLException, ClassNotFoundException {
        return petDao.getAll();
    }

    @Override
    public boolean deletePet(String petId) throws SQLException, ClassNotFoundException {
        return petDao.delete(petId);
    }

    @Override
    public boolean updatePet(PetDto dto) throws SQLException, ClassNotFoundException {
        return petDao.update(dto);
    }

    @Override
    public String getCustomerId(String text) throws SQLException {
        return customerDao.getCustomerId(text);
    }

    @Override
    public String getCustomerName(String ownerId) throws SQLException {
        return customerDao.getCustomerName(ownerId);
    }
}
