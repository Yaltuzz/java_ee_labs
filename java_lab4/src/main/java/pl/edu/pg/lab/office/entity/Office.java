package pl.edu.pg.lab.office.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@Entity
@Table(name = "offices")
public class Office implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String roomNumber;
    private int squareMeter;
    @ManyToOne
    @JoinColumn(name ="officeCenter")
    private OfficeCenter officeCenter;
    @ManyToOne
    @JoinColumn(name ="user")
    private User user;
}
