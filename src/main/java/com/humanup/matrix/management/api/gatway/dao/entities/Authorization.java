package com.humanup.matrix.management.api.gatway.dao.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@ToString(of= {"authorizationTitle","authorizationDescription","authorizationUrl"})
public class Authorization implements Serializable {
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
