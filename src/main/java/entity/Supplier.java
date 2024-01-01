package entity;

import lombok.*;

@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
@ToString
public class Supplier {
    private String suppId;
    private String location;
    private String name;
    private String type;
    private String contact;
}
