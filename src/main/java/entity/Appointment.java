package entity;

import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor@Setter
@Getter@NoArgsConstructor
@ToString
public class Appointment {
    private String appId;
    private String custId;
    private String type;
    private String time;
    private String  date;
}
