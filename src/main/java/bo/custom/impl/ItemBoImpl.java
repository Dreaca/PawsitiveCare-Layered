package bo.custom.impl;

import bo.custom.ItemBo;
import dao.DaoFactory;
import dao.custom.ItemDao;
import dto.ItemDto;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {
    private ItemDao itemDao = (ItemDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.ITEM);
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
       return itemDao.save(new Item(dto.getItemId(),dto.getDescription(),dto.getQtyOnHand(),dto.getUnitPrice()));
    }

    @Override
    public String getNextItemCode() throws SQLException {
       return itemDao.getNextItemCode();
    }

    @Override
    public ArrayList<ItemDto> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDao.getAll();
        ArrayList<ItemDto> list = new ArrayList<>();
        for (Item i: all) {
            list.add(new ItemDto(
               i.getItemId(),
               i.getDescription(),
               i.getQtyOnHand(),
               i.getUnitPrice()
            ));
        }
        return list;
    }

    @Override
    public List<String> getItemcodes() throws SQLException {
        return itemDao.getItemcodes();
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.update(new Item(dto.getItemId(),dto.getDescription(),dto.getQtyOnHand(),dto.getUnitPrice()));
    }
}
