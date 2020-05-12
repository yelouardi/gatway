package com.humanup.matrix.management.api.gatway.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of= {"accountMailAdresse","password","accountFirstName","accountLastName","accountRole"})
public class AccountVO {
     String accountMailAdresse;
     String password;
     String accountFirstName;
     String accountLastName;
     String accountRole;
}
