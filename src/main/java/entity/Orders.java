package entity;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Orders {
    private String orderId;
    private String custId;
    private LocalDate date;
}
