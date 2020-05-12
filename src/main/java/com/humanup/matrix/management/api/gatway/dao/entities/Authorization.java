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
@Table(name = "authorization")
@ToString(of= {"authorizationTitle","authorizationDescription","authorizationUrl"})
public class Authorization implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "authorization_id")
     Long authorizationId;
    @Column(name = "authorization_title")
     String authorizationTitle;
    @Column(name = "authorization_description")
     String authorizationDescription;
    @Column(name = "authorization_url")
     String authorizationUrl;
    @ManyToOne
    @JoinColumn(name = "role_id")
     Role role;


}
