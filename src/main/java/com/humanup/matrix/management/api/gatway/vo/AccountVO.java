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
public class AccountVO {
     String accountMailAdresse;
     String password;
     @JsonIgnore @Nullable
     String accountFirstName;
     @JsonIgnore
     String accountLastName;
     @JsonIgnore
     String accountRole;
}
