package dto;

import lombok.*;

import java.time.LocalDate;

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
