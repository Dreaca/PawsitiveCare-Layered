package Dto;

import Dto.Tm.OrderTm;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PlaceOrderDto {
    private String orderId;
    private String custId;
    private LocalDate date;
    private List<OrderTm> list;
}
