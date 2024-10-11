package pl.edu.pg.lab.officeCenter.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@ToString
@SuperBuilder
@EqualsAndHashCode
public class GetOfficeCentersResponse {

    @Getter
    @SuperBuilder
    @ToString
    @EqualsAndHashCode
    public static class OfficeCentersResponse {

        private String name;
        private String address;
        private int addressNumber;
    }

    @Singular
    private List<OfficeCentersResponse> officeCenters;


    public static Function<Collection<OfficeCenter>, GetOfficeCentersResponse> entityToDtoMapper() {
        return officeCenters -> {
            GetOfficeCentersResponse.GetOfficeCentersResponseBuilder response = GetOfficeCentersResponse.builder();
            officeCenters.stream()
                    .map(officeCenter -> OfficeCentersResponse.builder()
                            .name(officeCenter.getName())
                            .address(officeCenter.getAddress())
                            .addressNumber(officeCenter.getAddressNumber())
                            .build())
                    .forEach(response::officeCenter);
            return response.build();
        };
    }

}