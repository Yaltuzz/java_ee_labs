package pl.edu.pg.lab.office.dto;

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
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetOfficeResponse {

    private String roomNumber;
    private int squareMeters;
    private String username;
    private String officeName;

    public static Function<Office, GetOfficeResponse> entityToDtoMapper() {
        return office -> GetOfficeResponse.builder()
                .officeName(office.getName())
                .roomNumber(office.getRoomNumber())
                .squareMeters(office.getSquareMeter())
                .build();
    }
}
