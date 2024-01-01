package entity;

import lombok.*;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
@ToString
public class Item {
    private String itemId;
    private String description;
    private int qtyOnHand;
    private double unitPrice;
}
