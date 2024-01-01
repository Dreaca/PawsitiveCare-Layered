package entity;

import lombok.*;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
@ToString
public class Veterinarian {
    private String vetId;
    private String name;
    private String userId;
    private String contact;
    private String email;
}
