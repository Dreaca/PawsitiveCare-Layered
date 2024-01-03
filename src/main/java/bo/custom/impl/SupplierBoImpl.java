package bo.custom.impl;

import bo.custom.SupplierBo;
import dao.DaoFactory;
import dao.SQLUtil;
import dao.custom.SupplierDao;
import dto.SupplierDto;
import entity.Supplier;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBoImpl implements SupplierBo {
    private SupplierDao sDao = (SupplierDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.SUPPLIER);
    @Override
    public Object getSupplierIds() throws SQLException {
        return sDao.getSupplierIds();
    }

    @Override
    public boolean saveCustomer(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return sDao.save(new Supplier(dto.getSuppId(),
                dto.getLocation(),
                dto.getName(),
                dto.getType(),
                dto.getContact()));
    }

    @Override
    public SupplierDto getSupplierData(String supId) throws SQLException, ClassNotFoundException {
        Supplier search = sDao.search(supId);
        return new SupplierDto(search.getSuppId(),search.getLocation(),search.getName(),search.getType(),search.getContact());
    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return sDao.update(new Supplier(dto.getSuppId(),
                dto.getLocation(),
                dto.getName(),
                dto.getType(),
                dto.getContact()));
    }

    @Override
    public String generateSupplierId() throws SQLException {
        return sDao.generateSupplierId();
    }

    @Override
    public List<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> all = sDao.getAll();
        List<SupplierDto> list = new ArrayList<>();
        for (Supplier s: all) {
            list.add(new SupplierDto(
               s.getSuppId(),
               s.getLocation(),
               s.getName(),
               s.getType(),
               s.getContact()
            ));
        }
       return list;
    }
}
