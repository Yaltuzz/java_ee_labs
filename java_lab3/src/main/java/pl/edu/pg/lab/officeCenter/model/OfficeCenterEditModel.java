package pl.edu.pg.lab.officeCenter.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
public class OfficeCenterEditModel {

    private int id;
    private String name;
    private String address;
    private int addressNumber;
    public static Function<OfficeCenter, OfficeCenterEditModel> entityToModelMapper() {
        return officeCenter -> OfficeCenterEditModel.builder()
                .id(officeCenter.getId())
                .name(officeCenter.getName())
                .address(officeCenter.getAddress())
                .addressNumber(officeCenter.getAddressNumber())
                .build();
    }

    public static BiFunction<OfficeCenter, OfficeCenterEditModel, OfficeCenter> modelToEntityUpdater() {
        return (officeCenter, request) -> {
            officeCenter.setId(request.getId());
            officeCenter.setName(request.getName());
            officeCenter.setAddress(request.getAddress());
            officeCenter.setAddressNumber(request.getAddressNumber());
            return officeCenter;
        };
    }

}
