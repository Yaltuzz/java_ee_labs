package pl.edu.pg.lab.office.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.lab.office.entity.Office;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@ToString
@SuperBuilder
@EqualsAndHashCode
public class GetOfficesResponse {

    @Getter
    @SuperBuilder
    @ToString
    @EqualsAndHashCode
    public static class OfficeResponse {
        private String roomNumber;
        private int squareMeters;
        private String username;
        private String officeName;
    }

    @Singular
    private List<OfficeResponse> offices;


    public static Function<Collection<Office>, GetOfficesResponse> entityToDtoMapper() {
        return offices -> {
            GetOfficesResponse.GetOfficesResponseBuilder response = GetOfficesResponse.builder();
            offices.stream()
                    .map(office -> OfficeResponse.builder()
                            .officeName(office.getName())
                            .squareMeters(office.getSquareMeter())
                            .roomNumber(office.getRoomNumber())
                            .build())
                    .forEach(response::office);
            return response.build();
        };
    }

}