package dao.custom.Impl;

import dao.SQLUtil;
import dao.custom.SupplierDao;
import db.DbConnection;
import dto.SupplierDto;
import entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public List<String> getSupplierIds() throws SQLException {
        List<String> list = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT suppId FROM supplier");
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            list.add(
                    resultSet.getString(1)
            );
        }
        return list;
    }

    @Override
    public String generateSupplierId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT suppId FROM supplier ORDER BY suppId DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return getNext(resultSet.getString(1));
        }
        else return getNext(null);
    }

    private String getNext(String string) {
        if (string!=(null)){
            String[] id = string.split("SUP");
            int id1 = Integer.parseInt(id[1]);
            id1++;
            return String.format("SUP%03d",id1);
        }
        else return "SUP001";
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> list = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier");
        while (resultSet.next()){
            list.add(
                    new Supplier(
                            resultSet.getString("suppId"),
                            resultSet.getString("name"),
                            resultSet.getString("type"),
                            resultSet.getString("location"),
                            resultSet.getString("contact")
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
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier VALUES(?,?,?,?,?)",entity.getSuppId(),
                entity.getLocation(),
                entity.getName(),
                entity.getType(),
                entity.getContact());

    }

    @Override
    public boolean update(Supplier dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier SET name = ?, location = ?, type = ?, contact = ? WHERE suppId = ?",
                dto.getName(),
                dto.getLocation(),
                dto.getType(),
                dto.getContact(),
                dto.getSuppId());

    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        Supplier list = new Supplier();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier WHERE suppId = ?",id);
        while (resultSet.next()){
            list =(
                    new Supplier(
                            resultSet.getString("suppId"),
                            resultSet.getString("name"),
                            resultSet.getString("type"),
                            resultSet.getString("location"),
                            resultSet.getString("contact")
                    )
            );
        }
        return list;
    }
}
