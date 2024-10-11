package pl.edu.pg.lab.officeCenter.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.edu.pg.lab.office.entity.Office;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class OfficeCenterModel {

    private int id;
    private String name;
    private Date openDate;
    private String address;
    private int addressNumber;
    private List<Office> officeList;

    public static Function<OfficeCenter, OfficeCenterModel> entityToModelMapper() {
        return officeCenter -> OfficeCenterModel.builder()
                .id(officeCenter.getId())
                .name(officeCenter.getName())
                .address(officeCenter.getAddress())
                .addressNumber(officeCenter.getAddressNumber())
                .officeList(officeCenter.getOfficeList())
                .build();
    }
}
