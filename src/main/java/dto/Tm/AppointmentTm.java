package dto.Tm;

import com.jfoenix.controls.JFXButton;
import dto.AppointmentDto;
import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
public class AppointmentTm {
    private String appId;
    private String date;
    private String time;
    private String customer;
    private String contact;
    private AppointmentDto.AppType type;
    private Double price;
    private JFXButton mod;
}
