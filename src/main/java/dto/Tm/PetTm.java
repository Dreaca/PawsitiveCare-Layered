package dto.Tm;

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
    private int age;
    private String breed;
    private  String gender;
    private String color;
    private String owner;
    private JFXButton records;
    private JFXButton modifyButton;
}
