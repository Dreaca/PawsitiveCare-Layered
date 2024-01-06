package bo.custom.impl;

import bo.custom.SupplyOrderBo;
import dao.DaoFactory;
import dao.custom.ItemDao;
import dao.custom.ItemSupplierDao;
import dao.custom.SupplierDao;
import dto.SupplyOrderDto;
import entity.Item;
import entity.ItemSupplier;
import entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public class SupplyOrderBoImpl implements SupplyOrderBo {
    SupplierDao supplierDao = (SupplierDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.SUPPLIER);
    ItemDao itemDao = (ItemDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.ITEM);
    ItemSupplierDao iSdao = (ItemSupplierDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.ITEM_SUP);
    @Override
    public String getSupplierName(String suppId) throws SQLException, ClassNotFoundException {
        Supplier search = supplierDao.search(suppId);
        return search.getName();
    }

    @Override
    public List<String> getItemcodes() throws SQLException {
        return itemDao.getItemcodes();
    }

    @Override
    public List<String> getSupplierIds() throws SQLException {
        return supplierDao.getSupplierIds();
    }

    @Override
    public String getItemName(String value) throws SQLException, ClassNotFoundException {
        Item search = itemDao.search(value);
        return search.getDescription();
    }

    @Override
    public boolean placeSupplyOrder(SupplyOrderDto dto) throws SQLException, ClassNotFoundException {
       return iSdao.save(new ItemSupplier(dto.getSupplyOrderId(), dto.getItemId(), dto.getSupplierId(),dto.getQty(),dto.getDate()));
    }
}
