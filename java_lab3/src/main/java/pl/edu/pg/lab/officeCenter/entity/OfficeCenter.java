package pl.edu.pg.lab.officeCenter.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.lab.office.entity.Office;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
public class OfficeCenter implements Serializable {

    private int id;
    private String name;
    private Date openDate;
    private String address;
    private int addressNumber;
    private List<Office> officeList = new LinkedList<>();

}
