package bo;

import bo.custom.SuperBO;
import bo.custom.impl.*;

public class BOFactory {
    public  static BOFactory boFactory;
    private BOFactory(){}
    public static BOFactory getBOFactory(){
        return boFactory == null? new BOFactory() : boFactory;
    }
    public enum BoTypes{
        EMPLOYEE, APPOINTMENT,ITEM,PET,SCHEDULE, LOGIN,CUSTOMER,SUPPLIER,VET,RECORD
    }
    public SuperBO getBo(BoTypes types){
        switch (types){
            case EMPLOYEE:
                return new EmployeeBoImpl();
            case APPOINTMENT:
                return new AppointmentBoImpl();
            case ITEM:
                return new ItemBoImpl();
            case PET:
                return new PetBoImpl();
            case SCHEDULE:
                return new ScheduleBoImpl();
            case LOGIN:
                return new LoginBoImpl();
            case CUSTOMER:
                return new CustomerBoImpl();
            case SUPPLIER:
                return new SupplierBoImpl();
            case VET:
                return new VetBoImpl();
            case RECORD:
                return new RecordBoImpl();
            default:
                return null;
        }
    }
}
