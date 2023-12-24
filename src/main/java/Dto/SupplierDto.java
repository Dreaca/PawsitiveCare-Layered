package Dto;

import lombok.*;

@NoArgsConstructor
@ToString
@Setter
@Getter
@AllArgsConstructor
public class SupplierDto {
    private String suppId;
    private String location;
    private String name;
    private String type;
    private String contact;
}
