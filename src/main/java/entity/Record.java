package entity;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Record {
    private String petId;
    private String recordId;
    private String description;
    private LocalDate date;
}
