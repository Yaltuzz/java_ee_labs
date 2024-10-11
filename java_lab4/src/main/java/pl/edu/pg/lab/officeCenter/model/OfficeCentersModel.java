package pl.edu.pg.lab.officeCenter.model;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class OfficeCentersModel implements Serializable {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class OfficeCenter {
        private int id;
        private String name;
        private String address;
        private int addressNumber;
    }

    @Singular
    private List<OfficeCenter> officeCenters;
    public static Function<Collection<pl.edu.pg.lab.officeCenter.entity.OfficeCenter>, OfficeCentersModel> entityToModelMapper() {
        return officeCenters -> {
            OfficeCentersModel.OfficeCentersModelBuilder model = OfficeCentersModel.builder();
            officeCenters.stream()
                    .map(officeCenter -> OfficeCenter.builder()
                            .id(officeCenter.getId())
                            .name(officeCenter.getName())
                            .address(officeCenter.getAddress())
                            .addressNumber(officeCenter.getAddressNumber())
                            .build())
                    .forEach(model::officeCenter);
            return model.build();
        };
    }

}
