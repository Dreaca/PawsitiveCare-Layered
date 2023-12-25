package dao.custom;

import dao.CrudDao;
import dto.SupplierDto;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.List;

public interface SupplierDao extends CrudDao<SupplierDto> {
    List<String> getSupplierIds() throws SQLException;
}
