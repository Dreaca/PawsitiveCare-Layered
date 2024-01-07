package entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor@NoArgsConstructor
@Setter@Getter
@ToString
public class VetScheduleJoin {
    String scheduleId;
    LocalDate date;
    LocalTime time;
    String duration;
    String vetName;
}
