package Dto.Tm;

import javafx.scene.image.Image;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class EmployeeTm {
    private  String empId;
    private String name;
    private String address;
    private String contact;
    private Double salary;
    private String userId;
    private String NIC;

}
