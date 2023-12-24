package Dto.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetTm {
    private String petId;
    private String name;
    private String breed;
    private  String gender;
    private String color;
    private String owner;
    private String records;
    private JFXButton modifyButton;
}
