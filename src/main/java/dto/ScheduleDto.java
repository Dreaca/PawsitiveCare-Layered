package dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ScheduleDto {
    private String scheduleId;
    private LocalDate date;
    private LocalTime time;
    private String vetName;
    private  String duration;
}
