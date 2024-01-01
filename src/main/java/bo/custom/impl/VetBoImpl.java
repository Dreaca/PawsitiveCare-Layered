package bo.custom.impl;

import bo.custom.VetBo;
import dao.DaoFactory;
import dao.custom.VetDao;
import dto.VetDto;
import entity.Veterinarian;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VetBoImpl implements VetBo {
    private VetDao dao = (VetDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.VET);
    @Override
    public VetDto searchVet(String id) throws SQLException, ClassNotFoundException {
        Veterinarian search = dao.search(id);
        return new VetDto(search.getVetId(),search.getName(),search.getContact(),search.getEmail());
    }

    @Override
    public boolean deleteVet(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public boolean saveVeterinarian(VetDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Veterinarian(dto.getVetId(),dto.getVetName(),"Admin",dto.getContact(),dto.getEmail()));
    }

    @Override
    public List<VetDto> loadAllVets() throws SQLException, ClassNotFoundException {
        ArrayList<Veterinarian> all = dao.getAll();
        ArrayList<VetDto> list = new ArrayList<>();
        for (Veterinarian v: all) {
            list.add(new VetDto(
                    v.getVetId(),
                    v.getName(),
                    v.getContact(),
                    v.getEmail()
            ));
        }
        return list;
    }
}
