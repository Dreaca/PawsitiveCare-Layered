package dao;

import bo.SuperBO;
import dao.custom.Impl.*;

public class DaoFactory {
    public static DaoFactory daoFactory;
    public static DaoFactory getInstance(){
        return daoFactory == null? new DaoFactory() : daoFactory;
    }
    private DaoFactory(){

    }
    public enum DAOType{
        APPOINTMENT,CUSTOMER,EMPLOYEE,ITEM,LOGIN,ORDER,ORDER_DETAIL,PET,SCHEDULE,SUPPLIER,VET
    }
    public SuperDao getDAO(DAOType type){
        switch (type){
            case SCHEDULE:
                return new ScheduleDaoImpl();
            case PET:
                return new PetDaoImpl();
            case ITEM:
                return new ItemDaoImpl();
            case CUSTOMER:
                return  new CustomerDaoImpl();
            case ORDER:
                return new OrderDaoImpl();
            case ORDER_DETAIL:
                return new OrderDetailDaoImpl();
            case VET:
                return new VetDaoImpl();
            case LOGIN:
                return new LoginDaoImpl();
            case EMPLOYEE:
                return new EmployeeDaoImpl();
            case SUPPLIER:
                return new SupplierDaoImpl();
            case APPOINTMENT:
                return new AppointmentDAOImpl();
            default:
                return null;
        }
    }
}
