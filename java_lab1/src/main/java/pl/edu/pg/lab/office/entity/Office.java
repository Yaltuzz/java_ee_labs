package pl.edu.pg.lab.office.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.user.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Office {
    private String officeNumber;
    private int squareMeter;
    private OfficeCenter officeCenter;
    private User user;
}
