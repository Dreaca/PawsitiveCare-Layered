package dto.Tm;

import com.jfoenix.controls.JFXButton;
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
    private JFXButton delButton;
}
