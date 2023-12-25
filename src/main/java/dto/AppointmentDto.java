package dto;

import lombok.*;

import static dto.AppointmentDto.AppType.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class AppointmentDto {
    private String appId;
    private String customerName;
    private String contact;
    private AppType type;
    private String time;

    public AppointmentDto(String appId, String contact, AppType type, String time, String date, String customerId) {
        this.appId = appId;
        this.contact = contact;
        this.type = type;
        this.time = time;
        this.date = date;
        this.customerId = customerId;
    }

    public AppointmentDto(String appId, String customerName, String contact, AppType type, String time, String date, String customerId) {
        this.appId = appId;
        this.customerName = customerName;
        this.contact = contact;
        this.type = type;
        this.time = time;
        this.date = date;
        this.customerId = customerId;
    }

    private String date;

    public AppointmentDto(String appId, String customerName, String contact, AppType type, String time, String date) {
        this.appId = appId;
        this.customerName = customerName;
        this.contact = contact;
        this.type = type;
        this.time = time;
        this.date = date;
    }

    private String customerId;

    public AppointmentDto(String appId, String customerName, AppType type, String time, String date) {
        this.appId = appId;
        this.customerName = customerName;
        this.type = type;
        this.time = time;
        this.date = date;
    }

    public static enum AppType {
        SURGERY, VACCINATION, CHECKUP
    }
    public static AppType getvalueOf(String strings){
        if(strings.equals("Surgery")){
            return SURGERY;
        } else if (strings.equals("Vaccination")) {
            return VACCINATION;
        }
        else return CHECKUP;
    }
}
