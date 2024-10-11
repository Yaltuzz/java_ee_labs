package pl.edu.pg.lab.user.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.lab.user.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@SuperBuilder
@ToString
@EqualsAndHashCode
public class GetUsersResponse {

    @Getter
    @SuperBuilder
    @ToString
    @EqualsAndHashCode
    public static class UserResponse {

        String login;
        String firstname;
        String surname;
    }

    @Singular
    private List<UserResponse> users;


    public static Function<Collection<User>, GetUsersResponse> entityToDtoMapper() {
        return users -> {
            GetUsersResponse.GetUsersResponseBuilder response = GetUsersResponse.builder();
            users.stream()
                    .map(user -> UserResponse.builder()
                            .login(user.getLogin())
                            .firstname(user.getFirstname())
                            .surname(user.getSurname())
                            .build())
                    .forEach(response::user);
            return response.build();
        };
    }

}