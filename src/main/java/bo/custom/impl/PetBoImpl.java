package bo.custom.impl;

import bo.custom.PetBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dao.custom.PetDao;
import dto.PetDto;
import entity.Pet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetBoImpl implements PetBo {
    private PetDao petDao = (PetDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.PET);
    private CustomerDao customerDao = (CustomerDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.CUSTOMER);

    @Override
    public boolean savePet(PetDto dto) throws SQLException, ClassNotFoundException {
        return petDao.save(new Pet(dto.getPetId(),dto.getPetName(),dto.getAge(),dto.getPetBreed(),dto.getPetGender(),dto.getColor(),dto.getOwnerId()));
    }

    @Override
    public String getNextPetId() throws SQLException {
       return petDao.getNextPetId();
    }

    @Override
    public List<PetDto> getAllPets() throws SQLException, ClassNotFoundException {
        ArrayList<Pet> all = petDao.getAll();
        ArrayList<PetDto> list = new ArrayList<>();
        for (Pet p: all) {
            list.add(new PetDto(
               p.getPetId(),
               p.getName(),
               p.getAge(),
               p.getBreed(),
               p.getGender(),
               p.getColor(),
                    p.getCustId()
            ));
        }
        return list;
    }

    @Override
    public boolean deletePet(String petId) throws SQLException, ClassNotFoundException {
        return petDao.delete(petId);
    }

    @Override
    public boolean updatePet(PetDto dto) throws SQLException, ClassNotFoundException {
        return petDao.update(new Pet(dto.getPetId(),dto.getPetName(),dto.getAge(),dto.getPetBreed(),dto.getPetGender(),dto.getColor(),dto.getOwnerId()));
    }

    @Override
    public String getCustomerId(String text) throws SQLException {
        return customerDao.getCustomerId(text);
    }

    @Override
    public String getCustomerName(String ownerId) throws SQLException {
        return customerDao.getCustomerName(ownerId);
    }

    @Override
    public PetDto searchPet(String petId) throws SQLException, ClassNotFoundException {
        Pet search = petDao.search(petId);
        return new PetDto(search.getPetId(),
                search.getName(),
                search.getAge(),
                search.getBreed(),
                search.getGender(),
                search.getColor(),
                search.getCustId());
    }
}
