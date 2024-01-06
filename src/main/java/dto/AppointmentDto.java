package dto;

import lombok.*;

import static dto.AppointmentDto.AppType.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
@AllArgsConstructor
public class AppointmentDto {
    private String appId;
    private String customerName;
    private String contact;
    private AppType type;
    private String time;
    private String date;
    private Double price;
//    private String customerId;



    public static enum AppType {
        SURGERY, VACCINATION, CHECKUP;
        public static AppType getvalueOf(String strings){
            if(strings.equals("Surgery")){
                return SURGERY;
            } else if (strings.equals("Vaccination")) {
                return VACCINATION;
            }
            else return CHECKUP;
        }
    }

}
