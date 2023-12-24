package Dao;

import Dto.ItemDto;
import Dto.Tm.OrderTm;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao {
    List<ItemDto> getAllItems() throws SQLException;
    List<String> getItemCodes() throws SQLException;
    boolean updateItem(ItemDto dto) throws SQLException;
    String getNextItemCode() throws SQLException;
    String splitID(String string);
    boolean saveItem(ItemDto dto) throws SQLException;
    List<String> getItemcodes() throws SQLException;
    ItemDto getItemDesc(String value) throws SQLException;
    boolean updateItem(List<OrderTm> list) throws SQLException;
    boolean updateQty(String itemCode, int qty) throws SQLException;
}
