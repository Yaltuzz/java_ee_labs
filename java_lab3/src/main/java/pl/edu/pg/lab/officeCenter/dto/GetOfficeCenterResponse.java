package pl.edu.pg.lab.officeCenter.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.user.entity.User;
import pl.edu.pg.lab.user.entity.UserType;

import javax.ejb.Local;
import java.time.LocalDate;
import java.util.Date;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetOfficeCenterResponse {

    private String name;
    private Date openDate;
    private String address;
    private int addressNumber;

    public static Function<OfficeCenter, GetOfficeCenterResponse> entityToDtoMapper() {
        return officeCenter -> GetOfficeCenterResponse.builder()
                .name(officeCenter.getName())
                .address(officeCenter.getAddress())
                .openDate(officeCenter.getOpenDate())
                .addressNumber(officeCenter.getAddressNumber())
                .build();
    }
}
