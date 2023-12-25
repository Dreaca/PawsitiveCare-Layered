package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VetScheduleDto {
    private String scheduleId;
    private String vetId;
}
