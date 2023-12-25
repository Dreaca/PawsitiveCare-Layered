package bo;

public class BOFactory {
    public  static BOFactory boFactory;
    private BOFactory(){}
    public static BOFactory getBOFactory(){
        return boFactory == null? new BOFactory() : boFactory;
    }
    public enum BoTypes{
        EMPLOYEE, APPOINTMENT,ITEM,PET,SCHEDULE,ADMIN,CUSTOMER
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
            case ADMIN:
                return new AdminBoImpl();
            case CUSTOMER:
                return new CustomerBoImpl();
            default:
                return null;
        }
    }
}
