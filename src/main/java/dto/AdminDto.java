package dto;

import lombok.*;

@NoArgsConstructor
@Setter
@ToString
@Getter
@AllArgsConstructor
public class AdminDto {
    EmployeeDto dto;
    LoginFormDto loginFormDto;
}
