package bo.custom;

import dto.SupplyOrderDto;
import jdk.dynalink.linker.LinkerServices;

import java.sql.SQLException;
import java.util.List;

public interface SupplyOrderBo extends SuperBO{
    String getSupplierName(String suppId) throws SQLException, ClassNotFoundException;

    List<String> getItemcodes() throws SQLException;

    List<String> getSupplierIds() throws SQLException;

    String getItemName(String value) throws SQLException, ClassNotFoundException;

    boolean placeSupplyOrder(SupplyOrderDto supplyOrderDto) throws SQLException, ClassNotFoundException;
}
