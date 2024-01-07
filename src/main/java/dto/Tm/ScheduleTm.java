package dto.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleTm {
    private LocalDate date;
    private LocalTime time;
    private String vetName;
    private String duration;
    private JFXButton modButton;

}
