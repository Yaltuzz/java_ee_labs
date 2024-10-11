package pl.edu.pg.lab.officeCenter.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.lab.office.entity.Office;

import javax.persistence.*;
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
@Entity
@Table(name = "office_centers")
public class OfficeCenter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date openDate;
    private String address;
    private int addressNumber;

    @ToString.Exclude//It's common to exclude lists from toString
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "officeCenter", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Office> officeList;



}
