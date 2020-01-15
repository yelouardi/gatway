package com.humanup.matrix.management.api.gatway.dao.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Authorization {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
     Long authorizationId;
     String authorizationTitle;
     String authorizationDescription;
     String authorizationUrl;
    @ManyToOne
    @JoinColumn(name = "roleId")
     Role role;


}
