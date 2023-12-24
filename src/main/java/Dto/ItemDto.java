package Dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ItemDto {
    private String itemId;
    private String description;
    private int qtyOnHand;
    private double unitPrice;
}
