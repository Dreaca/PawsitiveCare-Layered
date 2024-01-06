package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.ItemSupplierDao;
import entity.ItemSupplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemSupplierDaoImpl implements ItemSupplierDao {
    @Override
    public ArrayList<ItemSupplier> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(ItemSupplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO item_supplier VALUES(?,?,?,?,?)",entity.getSuppOrderId(),entity.getItemId(),entity.getSuppId(),entity.getQty(),entity.getDate());
    }

    @Override
    public boolean update(ItemSupplier dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ItemSupplier search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
