package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PetDto {
    private String petId;
    private String petName;
    private int age;
    private String petBreed;
    private String petGender;
    private String color;
    private String ownerId;

    public PetDto(String string) {
        this.petId = string;
    }
}
