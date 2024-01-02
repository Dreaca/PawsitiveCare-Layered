package bo.custom;

import dto.ItemDto;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBo extends SuperBO{
    boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException;

    String getNextItemCode() throws SQLException;

    List<ItemDto> getAllItems() throws SQLException, ClassNotFoundException;

    List<String> getItemcodes() throws SQLException;

    boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException;

    ItemDto searchItem(String value) throws SQLException, ClassNotFoundException;

}
