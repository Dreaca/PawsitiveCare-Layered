package Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PetDto {
    private String petId;
    private String petName;
    private String petBreed;
    private String petGender;
    private String ownerId;
    private String color;

    public PetDto(String string) {
        this.petId = string;
    }
}
