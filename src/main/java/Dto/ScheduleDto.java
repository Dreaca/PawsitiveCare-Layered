package Dto;

import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ScheduleDto {
    private String scheduleId;
    private LocalDate date;
    private  String duration;
    private String  time;
    private String vetName;
}
