package bo;

import dao.DaoFactory;
import dao.custom.Impl.ItemDaoImpl;
import dao.custom.ItemDao;
import dto.ItemDto;

import java.sql.SQLException;

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
}
