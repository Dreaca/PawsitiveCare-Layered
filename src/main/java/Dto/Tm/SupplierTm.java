package Dto.Tm;

import javafx.scene.control.Hyperlink;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SupplierTm {
    private String suppId;
    private String suppName;
    private String type;
    private String location;
    private String contact;
    private Hyperlink invoice;
}
