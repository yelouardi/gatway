package com.humanup.matrix.management.api.gatway.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Account {
    @Id
    @Column(unique = true)
    @Email(message = "*Please provide a valid email")
    @NotEmpty(message = "*Please provide an email")
     String accountMailAdresse;
    @NotEmpty(message = "*Please provide your password")
     String password;
     String accountFirstName;
     String accountLastName;
    @ManyToOne
    @JoinColumn(name = "roleId")
     Role role;
}
