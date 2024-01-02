package bo.custom.impl;

import bo.custom.OrdersBO;
import dao.DaoFactory;
import dao.custom.ItemDao;
import dao.custom.OrderDao;
import dao.custom.OrderDetailDao;
import dao.custom.Transaction;
import dto.PlaceOrderDto;
import entity.OrderDetail;
import entity.Orders;

import java.sql.SQLException;
import java.time.LocalDate;

public class OrdersBoImpl implements OrdersBO {
    OrderDao order = (OrderDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.ORDER);
    ItemDao item = (ItemDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.ITEM);
    OrderDetailDao od = (OrderDetailDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.ORDER_DETAIL);
    @Override
    public boolean saveOrder(PlaceOrderDto placeOrderDto) throws SQLException, ClassNotFoundException {
        String orderId = placeOrderDto.getOrderId();
        String custId = placeOrderDto.getCustId();
        LocalDate date = placeOrderDto.getDate();
        Transaction.setAutoCommit(false);
        boolean save = order.save(new Orders(orderId, custId, date));
        if (!save) {
            Transaction.rollback();
            Transaction.setAutoCommit(true);
        }
        boolean b = item.updateItem(placeOrderDto.getList());
            if (!b) {
                Transaction.rollback();
                Transaction.setAutoCommit(true);
            }
        boolean saveOrder = od.saveOrderDetails(placeOrderDto.getOrderId(), placeOrderDto.getList());
                if (!saveOrder) {
                    Transaction.rollback();
                    Transaction.setAutoCommit(true);
                    return false;
                }
                Transaction.commit();
                Transaction.setAutoCommit(true);
                return true;
            }

    @Override
    public String generateNextOrderId() throws SQLException {
       return order.generateNextOrderId();
    }

}
