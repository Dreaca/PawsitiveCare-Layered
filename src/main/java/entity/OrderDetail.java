package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
    private String orderId;
    private String itemId;
    private int qty;
    private double unitPrice;
}
