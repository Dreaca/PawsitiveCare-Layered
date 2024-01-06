package dto;

import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor@NoArgsConstructor
@ToString
@Getter@Setter
public class SupplyOrderDto {
    String supplyOrderId;
    String itemId;
    String supplierId;
    int qty;
    LocalDate date;
}
