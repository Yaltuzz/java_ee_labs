package pl.edu.pg.lab.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
public class GetUserResponse {

    private String login;

    private String firstName;

    private String surname;

    private LocalDate birthdayDate;

    private UserType type;

    public static Function<User, GetUserResponse> entityToDtoMapper() {
        return user -> GetUserResponse.builder()
                .login(user.getLogin())
                .firstName(user.getFirstname())
                .surname(user.getSurname())
                .birthdayDate(user.getBirthdayDate())
                .type(user.getType())
                .build();
    }

}
