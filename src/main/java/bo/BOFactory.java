package bo;

public class BOFactory {
    public  static BOFactory boFactory;
    private BOFactory(){}
    public static BOFactory getBOFactory(){
        return boFactory == null? new BOFactory() : boFactory;
    }
    public enum BoTypes{
        ADDEMPLOYEE,ADDAPPOINTMENT
    }
    public SuperBO getBo(BoTypes types){
        switch (types){
            case ADDEMPLOYEE:
                return new AddNewEmployeeFormBoImpl();
            case ADDAPPOINTMENT:
                return new AddNewAppointmentBoImpl();
            default:
                return null;
        }
    }
}
