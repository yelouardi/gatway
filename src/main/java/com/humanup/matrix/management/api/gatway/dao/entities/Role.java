package com.humanup.matrix.management.api.gatway.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "role")
@ToString(of= {"roleTitle","roleDescription","authorizationList"})
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
     Long roleId;
     String roleTitle;
     String roleDescription;

    @OneToMany(mappedBy="role",fetch=FetchType.LAZY)
     List<Account> accountList;

    @OneToMany(mappedBy="role",fetch=FetchType.EAGER)
     List<Authorization> authorizationList;

    public String[] getPathAllowed(){
        return this.authorizationList.stream()
                .filter(a -> null!=a)
                .map(a ->a.getAuthorizationUrl())
                .collect(Collectors.toList()).toArray(String[]::new);
    }
}
