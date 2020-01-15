package com.humanup.matrix.management.api.gatway.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
     Long roleId;
     String roleTitle;
     String roleDescription;

    @OneToMany(mappedBy="role",fetch=FetchType.LAZY)
     List<Account> accountList;

    @OneToMany(mappedBy="role",fetch=FetchType.EAGER)
     List<Authorization> authorizationList;

}
