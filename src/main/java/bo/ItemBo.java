package bo;

import dto.ItemDto;

import java.sql.SQLException;

public interface ItemBo extends SuperBO{
    boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException;

    String getNextItemCode() throws SQLException;
}
