package pl.edu.pg.lab.user.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.security.AuthProvider;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
public class User implements Serializable {
    private String login;
    private String firstname;
    private String surname;
    private LocalDate birthdayDate;
    private UserType type;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private byte[] portrait;

}

