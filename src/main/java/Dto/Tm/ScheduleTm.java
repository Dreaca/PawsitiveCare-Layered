package Dto.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleTm {
    private LocalDate date;
    private String time;
    private String vetName;
    private String duration;
    private JFXButton modButton;

}
