package pl.edu.pg.lab.officeCenter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfficeCenter {

    private String name;
    private Date openDate;
    private String address;
    private int addressNumber;

}
