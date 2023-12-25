package entity;

import lombok.*;

import java.time.LocalDate;
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Schedule {
    private String scheduleId;
    private LocalDate date;
    private String duration;
    private String time;
}
