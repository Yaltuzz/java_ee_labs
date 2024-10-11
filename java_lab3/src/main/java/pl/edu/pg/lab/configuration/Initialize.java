package pl.edu.pg.lab.configuration;

import pl.edu.pg.lab.office.entity.Office;
import pl.edu.pg.lab.office.service.OfficeService;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.officeCenter.service.OfficeCenterService;
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
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class Initialize {

    private final UserService userService;
    private final OfficeCenterService officeCenterService;
    private final OfficeService officeService;


    @Inject
    public Initialize(UserService userService,OfficeCenterService officeCenterService,OfficeService officeService) {
        this.userService = userService;
        this.officeCenterService = officeCenterService;
        this.officeService = officeService;
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

        Office flow = Office.builder()
                .id(1)
                .name("Flow")
                .roomNumber("101A")
                .squareMeter(120)
                .build();
        Office flow2 = Office.builder()
                .id(2)
                .name("Flow")
                .roomNumber("101B")
                .squareMeter(60)
                .build();
        Office flow3 = Office.builder()
                .id(3)
                .name("Kosmos")
                .roomNumber("103B")
                .squareMeter(25)
                .build();

        Office flow4 = Office.builder()
                .id(4)
                .name("slonce")
                .roomNumber("120B")
                .squareMeter(30)
                .build();


        List<Office> listOfficeGateA = new LinkedList<>();
        List<Office> listOfficeTower = new LinkedList<>();

        listOfficeGateA.add(flow);
        listOfficeGateA.add(flow2);
        listOfficeGateA.add(flow3);
        listOfficeTower.add(flow4);

        OfficeCenter gateA = OfficeCenter.builder()
                .id(1)
                .name("Olivia Gate A")
                .address("Grunwaldzka")
                .addressNumber(142)
                .officeList(listOfficeGateA)
                .build();

        OfficeCenter tower = OfficeCenter.builder()
                .id(2)
                .name("Olivia Tower")
                .address("Biala")
                .addressNumber(172)
                .officeList(listOfficeTower)
                .build();
        OfficeCenter prime = OfficeCenter.builder()
                .id(3)
                .name("Olivia Prime")
                .address("Grunwaldzka")
                .addressNumber(144)
                .build();
        OfficeCenter star = OfficeCenter.builder()
                .id(4)
                .name("Olivia Star")
                .address("Grunwaldzka")
                .addressNumber(143)
                .build();
        officeService.create(flow);
        officeService.create(flow2);
        officeService.create(flow3);
        officeService.create(flow4);
        officeCenterService.create(gateA);
        officeCenterService.create(tower);
        officeCenterService.create(prime);
        officeCenterService.create(star);
    }
    private byte[] getResourceAsByteArray(String name) throws IOException {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }

}
