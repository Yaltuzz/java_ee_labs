package pl.edu.pg.lab.user.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.lab.office.entity.Office;

import javax.persistence.*;
import java.io.Serializable;
import java.security.AuthProvider;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    private String login;
    private String firstname;
    private String surname;
    private LocalDate birthdayDate;
    private UserType type;
    @ToString.Exclude//It's common to exclude lists from toString
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Office> officeList;
}

