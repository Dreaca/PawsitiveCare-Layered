package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Pet {
    private String petId;
    private String name;
    private int age;
    private String breed;
    private String gender;
    private String color;
    private String custId;
}
