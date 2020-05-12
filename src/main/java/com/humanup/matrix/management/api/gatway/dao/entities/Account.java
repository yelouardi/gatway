package com.humanup.matrix.management.api.gatway.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "account")
@ToString(of= {"accountMailAdresse","accountFirstName","accountLastName","role"})
public class Account implements Serializable {
    @Id
    @Column(name = "account_mail_adresse",unique = true)
    @Email(message = "*Please provide a valid email")
    @NotEmpty(message = "*Please provide an email")
     String accountMailAdresse;
    @NotEmpty(message = "*Please provide your password")
     String password;
    @Column(name = "account_first_name")
     String accountFirstName;
     @Column(name = "account_last_name")
     String accountLastName;
    @ManyToOne
    @JoinColumn(name = "role_id")
     Role role;
}
