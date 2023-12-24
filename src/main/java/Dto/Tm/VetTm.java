package Dto.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class VetTm {
    private String vetId;
    private String name;
    private String contact;
    private String email;
    private JFXButton schedule;
}
