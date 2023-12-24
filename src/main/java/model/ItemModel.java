package model;

import Db.DbConnection;
import Dto.ItemDto;
import Dto.Tm.OrderTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {
    public List<ItemDto> getAllItems() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM item");
        ResultSet resultSet = pstm.executeQuery();
        List<ItemDto> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(
                    new ItemDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getDouble(4)
                    )
            );
        }
        return  list;
    }

    public List<String> getItemCodes() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT itemId from item");
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> list = new ArrayList<>();
        while(resultSet.next()){
            list.add(
                    resultSet.getString(1)
            );
        }
        return list;
    }

    public boolean updateItem(ItemDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE item SET description = ?, qtyOnHand = ?, unitPrice = ? WHERE itemId = ?");
        pstm.setString(1,dto.getDescription());
        pstm.setInt(2,dto.getQtyOnHand());
        pstm.setDouble(3,dto.getUnitPrice());
        pstm.setString(4,dto.getItemId());
        int i = pstm.executeUpdate();
        return i>0;
    }

    public String getNextItemCode() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT itemId FROM item ORDER BY itemId DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
        return splitID(resultSet.getString(1));
        }
        else return "I001";
    }

    private String splitID(String string) {
        if(string!=null){
        String [] id = string.split("I0");
        int idnum = Integer.parseInt(id[1]);
        idnum++;
        return "I00"+idnum;
        }
        else return "I001";
    }

    public boolean saveItem(ItemDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO item VALUES(?,?,?,?)");
        pstm.setString(1,dto.getItemId());
        pstm.setString(2,dto.getDescription());
        pstm.setInt(3,dto.getQtyOnHand());
        pstm.setDouble(4,dto.getUnitPrice());
        int i = pstm.executeUpdate();
        return i>0;
    }

    public List<String> getItemcodes() throws SQLException {
        List<String> list = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT itemId FROM item");
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            list.add(
                    resultSet.getString(1)
            );
        }
        return list;
    }

    public ItemDto getItemDesc(String value) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        var dto = new ItemDto();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM item WHERE itemId = ?");
        pstm.setString(1, (String) value);
        ResultSet resultSet = pstm.executeQuery();
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

    public boolean updateItem(List<OrderTm> list) throws SQLException {
        for (OrderTm tm : list) {
            if(!updateQty(tm.getItemCode(),tm.getQty())){
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String itemCode, int qty) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE item SET qtyOnHand = qtyOnHand -  ? WHERE itemId = ?");
        pstm.setInt(1,qty);
        pstm.setString(2,itemCode);
        return pstm.executeUpdate()>0;
    }
}
