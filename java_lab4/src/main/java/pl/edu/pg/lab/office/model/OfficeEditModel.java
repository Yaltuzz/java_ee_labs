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
import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class OfficeEditModel {

    private int id;
    private String name;
    private String roomNumber;
    private String squareMeter;

    public static Function<Office, OfficeEditModel> entityToModelMapper() {
        return office -> OfficeEditModel.builder()
                .id(office.getId())
                .name(office.getName())
                .roomNumber(office.getRoomNumber())
                .squareMeter(String.valueOf(office.getSquareMeter()))
                .build();
    }

    public static BiFunction<Office, OfficeEditModel, Office> modelToEntityUpdater() {
        return (office, request) -> {
            office.setId(request.getId());
            office.setName(request.getName());
            office.setRoomNumber(request.getRoomNumber());
            office.setSquareMeter(Integer.parseInt(request.getSquareMeter()));
            return office;
        };
    }

}
