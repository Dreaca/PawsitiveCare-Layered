package dao.custom.Impl;

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
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(Supplier dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Supplier dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
