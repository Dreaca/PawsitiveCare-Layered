package bo.custom.impl;

import bo.custom.OrdersBO;
import dao.DaoFactory;
import dao.custom.*;
import dto.PlaceOrderDto;
import entity.Customer;
import entity.OrderDetail;
import entity.Orders;
import net.sf.jasperreports.charts.base.ChartCopyBaseObjectFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdersBoImpl implements OrdersBO {
    CustomerDao cus = (CustomerDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.CUSTOMER);
    OrderDao order = (OrderDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.ORDER);
    ItemDao item = (ItemDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.ITEM);
    OrderDetailDao od = (OrderDetailDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.ORDER_DETAIL);
    @Override
    public boolean saveOrder(PlaceOrderDto placeOrderDto) throws SQLException, ClassNotFoundException {
        String orderId = placeOrderDto.getOrderId();
        String custId = placeOrderDto.getCustId();
        LocalDate date = placeOrderDto.getDate();
        boolean flag = false;
        Transaction.setAutoCommit(false);
        boolean save = order.save(new Orders(orderId, custId, date));
        if (save) {
            boolean b = item.updateItem(placeOrderDto.getList());
            if (b) {
                boolean saveOrder = od.saveOrderDetails(placeOrderDto.getOrderId(), placeOrderDto.getList());
                if (saveOrder) {
                    Transaction.commit();
                    Transaction.setAutoCommit(true);
                    flag = true;
                }
            }
        } else {
            Transaction.rollback();
            Transaction.setAutoCommit(true);
            flag = false;
        }
        return flag;
    }

    @Override
    public String generateNextOrderId() throws SQLException {
       return order.generateNextOrderId();
    }

    @Override
    public List<String> getCustomerNameList() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = cus.getAll();
        List<String> list = new ArrayList<>();
        for (Customer c: all) {
            list.add(c.getName());
        }
        return list;
    }

}
