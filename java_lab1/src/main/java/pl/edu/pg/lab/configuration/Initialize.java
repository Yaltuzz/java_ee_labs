package pl.edu.pg.lab.configuration;

import pl.edu.pg.lab.user.entity.User;
import pl.edu.pg.lab.user.entity.UserType;
import pl.edu.pg.lab.user.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

@ApplicationScoped
public class Initialize {

    private final UserService userService;

    @Inject
    public Initialize(UserService userService) {
        this.userService = userService;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) throws IOException {
        init();
    }
    private synchronized void init() throws IOException {
        User jan = User.builder()
                .login("jano")
                .firstname("Jan")
                .surname("Kowalski")
                .birthdayDate(LocalDate.of(1999,11,23))
                .type(UserType.SMALLBUSINNES)
                .portrait(getResourceAsByteArray("/avatar111.png"))
                .build();

        User tomasz = User.builder()
                .login("jakub")
                .firstname("Jakub")
                .surname("Wisniewski")
                .birthdayDate(LocalDate.of(1980, 3, 27))
                .portrait(getResourceAsByteArray("/avatar111.png"))
                .build();

        User mariusz = User.builder()
                .login("mario")
                .firstname("Mariusz")
                .surname("Sloneczny")
                .birthdayDate(LocalDate.of(1960, 1, 21))
                .portrait(getResourceAsByteArray("/avatar111.png"))
                .build();

        User andreas = User.builder()
                .login("andre")
                .firstname("Andreas")
                .surname("Messi")
                .birthdayDate(LocalDate.of(1987, 6, 17))
                .portrait(getResourceAsByteArray("/avatar111.png"))
                .build();

        userService.create(jan);
        userService.create(tomasz);
        userService.create(mariusz);
        userService.create(andreas);
    }
    private byte[] getResourceAsByteArray(String name) throws IOException {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }

}
