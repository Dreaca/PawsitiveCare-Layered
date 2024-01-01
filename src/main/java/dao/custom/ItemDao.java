package dao.custom;

import dao.CrudDao;
import dto.ItemDto;
import dto.Tm.OrderTm;
import entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao extends CrudDao<Item> {
    List<String> getItemCodes() throws SQLException;
    String getNextItemCode() throws SQLException;
    String splitID(String string);

    List<String> getItemcodes() throws SQLException;
    ItemDto getItemDesc(String value) throws SQLException;
    boolean updateItem(List<OrderTm> list) throws SQLException;
    boolean updateQty(String itemCode, int qty) throws SQLException;
}
