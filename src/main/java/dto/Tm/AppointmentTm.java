package dto.Tm;

import dto.AppointmentDto;
import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
public class AppointmentTm {
    private String date;
    private String time;
    private String customer;
    private String contact;
    private AppointmentDto.AppType type;
    private Double price;
}
