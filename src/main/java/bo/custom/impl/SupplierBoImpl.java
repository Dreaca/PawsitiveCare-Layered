package bo.custom.impl;

import bo.custom.SupplierBo;
import dao.DaoFactory;
import dao.custom.SupplierDao;

import java.sql.SQLException;

public class SupplierBoImpl implements SupplierBo {
    private SupplierDao sDao = (SupplierDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.SUPPLIER);
    @Override
    public Object getSupplierIds() throws SQLException {
        return sDao.getSupplierIds();
    }
}
