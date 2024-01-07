package dao;

import dao.custom.Impl.*;

public class DaoFactory {
    static DaoFactory daoFactory;
    public static DaoFactory getInstance(){
        return (daoFactory == null)? daoFactory =  new DaoFactory() : daoFactory;
    }
    private DaoFactory(){

    }
    public enum DAOType{
        APPOINTMENT,CUSTOMER,EMPLOYEE,ITEM,LOGIN,ORDER,ORDER_DETAIL,PET,SCHEDULE,SUPPLIER,VET,VS,RECORD,ITEM_SUP,QUERY
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
            case VS:
                return new VetScheduleDaoImpl();
            case RECORD:
                return new RecordDaoImpl();
            case ITEM_SUP:
                return new ItemSupplierDaoImpl();
            case QUERY:
                return new QueryDaoImpl();
            default:
                return null;
        }
    }
}
