package Dto.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Setter
@Getter
public class ItemTm {
    private String itemCode;
    private String description;
    private int QTO;
    private double unitPrice;
    private JFXButton deletebutton;
}
