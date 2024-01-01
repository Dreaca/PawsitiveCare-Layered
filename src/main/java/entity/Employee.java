package entity;

import lombok.*;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
@ToString
public class Employee {
    private String  employeeId;
    private String name;
    private String address;
    private String contact;
    private double salary;
    private String userId;
    private String NIC;
}
