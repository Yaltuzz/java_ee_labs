package pl.edu.pg.lab.office.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.user.entity.User;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
public class Office implements Serializable {
    private int id;
    private String name;
    private String roomNumber;
    private int squareMeter;
    private OfficeCenter officeCenter;
    private User user;
}
