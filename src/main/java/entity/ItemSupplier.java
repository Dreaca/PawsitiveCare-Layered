package entity;

import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
@ToString
public class ItemSupplier {
   private String suppOrderId;
   private String itemId;
   private String suppId;
   private String qty;
   private LocalDate date;
}
