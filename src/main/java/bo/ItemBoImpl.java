package bo;

import dao.DaoFactory;
import dao.custom.ItemDao;
import dto.ItemDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo{
    private ItemDao itemDao = (ItemDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.ITEM);
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
       return itemDao.save(dto);
    }

    @Override
    public String getNextItemCode() throws SQLException {
       return itemDao.getNextItemCode();
    }

    @Override
    public ArrayList<ItemDto> getAllItems() throws SQLException, ClassNotFoundException {
        return itemDao.getAll();
    }

    @Override
    public List<String> getItemcodes() throws SQLException {
        return itemDao.getItemcodes();
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.update(dto);
    }
}
