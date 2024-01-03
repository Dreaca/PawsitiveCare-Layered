package dao.custom;

import dao.CrudDao;
import dto.SupplierDto;
import entity.Supplier;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.List;

public interface SupplierDao extends CrudDao<Supplier> {
    List<String> getSupplierIds() throws SQLException;

    String generateSupplierId() throws SQLException;
}
