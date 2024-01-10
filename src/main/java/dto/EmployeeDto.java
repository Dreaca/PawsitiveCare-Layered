package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class EmployeeDto {
        private String empId;
        private String empName;
        private String address;
        private String contact;
        private double salary;
        private  String userId;
        private String NIC;
}
