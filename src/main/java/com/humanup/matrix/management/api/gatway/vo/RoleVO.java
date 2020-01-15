package com.humanup.matrix.management.api.gatway.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RoleVO {
     String roleTitle;
     String roleDescription;
     int countAccount;
}
