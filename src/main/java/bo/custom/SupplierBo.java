package bo.custom;

import dto.SupplierDto;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBo extends SuperBO {
    Object getSupplierIds() throws SQLException;

    boolean saveCustomer(SupplierDto dto) throws SQLException, ClassNotFoundException;

    SupplierDto getSupplierData(String supId) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;

    String generateSupplierId() throws SQLException;

    List<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException;
}
