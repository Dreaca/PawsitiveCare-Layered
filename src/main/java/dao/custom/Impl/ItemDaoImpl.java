package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.ItemDao;
import dto.ItemDto;
import dto.Tm.OrderTm;
import entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public ArrayList<Item> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM item");
        ArrayList<Item> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(
                    new Item(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getDouble(4)
                    )
            );
        }
        return list;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getItemCodes() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT itemId from item");
        ArrayList<String> list = new ArrayList<>();
        while(resultSet.next()){
            list.add(
                    resultSet.getString(1)
            );
        }
        return list;
    }

    @Override
    public boolean update(Item entity) throws SQLException {
        return SQLUtil.execute("UPDATE item SET description = ?, qtyOnHand = ?, unitPrice = ? WHERE itemId = ?",entity.getDescription(),
                entity.getQtyOnHand(),
                entity.getUnitPrice(),
                entity.getItemId());
    }

    @Override
    public Item search(String id) throws SQLException, ClassNotFoundException {
        ResultSet re = SQLUtil.execute("SELECT * FROM item WHERE itemId = ? ", id);
        re.next();
        return new Item(re.getString(1),re.getNString(2),re.getInt(3),re.getDouble(4));
    }

    @Override
    public String getNextItemCode() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT itemId FROM item ORDER BY itemId DESC LIMIT 1");
        if (resultSet.next()){
            return splitID(resultSet.getString(1));
        }
        else return "I001";
    }

    @Override
    public String splitID(String string) {
        if(string!=null){
            String [] id = string.split("I0");
            int idnum = Integer.parseInt(id[1]);
            idnum++;
            return "I00"+idnum;
        }
        else return "I001";
    }

    @Override
    public boolean save(Item entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO item VALUES(?,?,?,?)",entity.getItemId(),entity.getDescription(),entity.getQtyOnHand(),entity.getUnitPrice());
    }

    @Override
    public List<String> getItemcodes() throws SQLException {
        List<String> list = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT itemId FROM item");
        while (resultSet.next()){
            list.add(
                    resultSet.getString(1)
            );
        }
        return list;
    }

    @Override
    public ItemDto getItemDesc(String value) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM item WHERE itemId = ?",value);
        if (resultSet.next()) {
            return new ItemDto(
                    resultSet.getString("itemId"),
                    resultSet.getString("description"),
                    resultSet.getInt("qtyOnHand"),
                    resultSet.getDouble("unitPrice")
            );
        }
        else return null;
    }
    @Override
    public boolean updateItem(List<OrderTm> list) throws SQLException {
        for (OrderTm tm : list) {
            if(!updateQty(tm.getItemCode(),tm.getQty())){
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean updateQty(String itemCode, int qty) throws SQLException {
        return SQLUtil.execute("UPDATE item SET qtyOnHand = qtyOnHand -  ? WHERE itemId = ?",qty,itemCode);
    }
}
