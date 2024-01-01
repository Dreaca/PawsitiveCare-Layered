package entity;

import lombok.*;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
@ToString
public class User {
    private String userId;
    private String userName;
    private String passWord;
}
