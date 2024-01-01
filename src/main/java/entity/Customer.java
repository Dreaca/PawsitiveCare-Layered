package entity;

import lombok.*;

@AllArgsConstructor@Setter
@Getter@NoArgsConstructor
@ToString
public class Customer {
    private String custId;
    private String name;
    private String address;
    private String contact;
}
