package pl.edu.pg.lab.office.model;

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
public class OfficeModel {

    private int id;
    private String name;
    private String roomNumber;
    private double squareMeter;

    public static Function<Office, OfficeModel> entityToModelMapper() {
        return office -> OfficeModel.builder()
                .id(office.getId())
                .name(office.getName())
                .roomNumber(office.getRoomNumber())
                .squareMeter(office.getSquareMeter())
                .build();
    }
}
