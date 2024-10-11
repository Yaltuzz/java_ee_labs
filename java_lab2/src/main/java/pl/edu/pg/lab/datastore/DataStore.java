package pl.edu.pg.lab.datastore;

import pl.edu.pg.lab.office.entity.Office;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.serialization.CloningUtility;
import pl.edu.pg.lab.user.entity.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class DataStore {

    private Set<User> users = new HashSet<>();
    private Set<OfficeCenter> officeCenters = new HashSet<>();
    private Set<Office> offices = new HashSet<>();


    public synchronized Optional<Office> findOffice(int id) {
        return offices.stream()
                .filter(office -> office.getId()==id)
                .findFirst()
                .map(CloningUtility::clone);
    }
    public synchronized List<Office> findAllOffices() {
        return offices.stream()
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized void createOffice(Office office) throws IllegalArgumentException {
        findUser(office.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The user login \"%s\" is not unique", office.getName()));
                },
                () -> offices.add(CloningUtility.clone(office)));
    }

    public synchronized void updateOffice(Office office) throws IllegalArgumentException {
        findOffice(office.getId()).ifPresentOrElse(
                original -> {
                    OfficeCenter officeCenter = findOfficeCenterByOfficeId(office.getId()).get();
                    boolean removed = officeCenter.getOfficeList().remove(original);
                    officeCenter.getOfficeList().add(office);
                    updateOfficeCenter(officeCenter);
                    offices.remove(original);
                    offices.add(CloningUtility.clone(office));
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The character with id \"%d\" does not exist", office.getName()));
                });
    }

    public synchronized Optional<OfficeCenter> findOfficeCenter(int id) {
        return officeCenters.stream()
                .filter(officeCenter -> officeCenter.getId()==id)
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized Optional<OfficeCenter> findOfficeCenterByOfficeId(int id) {
        return officeCenters.stream()
                .filter(officeCenter -> {
                    boolean state = false;
                    if (officeCenter.getOfficeList() != null) {
                        for (Office x : officeCenter.getOfficeList()) {
                            if (x.getId() == id) {
                                state = true;
                            }
                        }
                    }
                    return state;
                })
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized List<OfficeCenter> findAllOfficeCenters() {
        return officeCenters.stream()
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }


    public synchronized void createOfficeCenter(OfficeCenter officeCenter) throws IllegalArgumentException {
        findUser(officeCenter.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The user login \"%s\" is not unique", officeCenter.getName()));
                },
                () -> officeCenters.add(CloningUtility.clone(officeCenter)));
    }

    public synchronized void updateOfficeCenter(OfficeCenter officeCenter) throws IllegalArgumentException {
        findOfficeCenter(officeCenter.getId()).ifPresentOrElse(
                original -> {
                    officeCenters.remove(original);
                    officeCenters.add(CloningUtility.clone(officeCenter));
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The character with id \"%d\" does not exist", officeCenter.getName()));
                });
    }
    public synchronized Optional<User> findUser(String login) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized List<User> findAllUsers() {
        return users.stream()
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized void createUser(User user) throws IllegalArgumentException {
        findUser(user.getLogin()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The user login \"%s\" is not unique", user.getLogin()));
                },
                () -> users.add(CloningUtility.clone(user)));
    }

    public synchronized void updateUser(User user) throws IllegalArgumentException {
        findUser(user.getLogin()).ifPresentOrElse(
                original -> {
                    users.remove(original);
                    users.add(CloningUtility.clone(user));
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The character with id \"%d\" does not exist", user.getLogin()));
                });
    }

    public synchronized void deleteOfficeCenter(int id) throws IllegalArgumentException {
        findOfficeCenter(id).ifPresentOrElse(
                original -> {
                    List<Office> officeList = original.getOfficeList();
                    if (officeList != null) {
                        for (Office x : officeList) {
                            deleteOffice(x.getId());
                        }
                    }
                    officeCenters.remove(original);
                    },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The character with id \"%d\" does not exist", id));
                });
    }
    public synchronized void deleteOffice(int id) throws IllegalArgumentException {
        findOffice(id).ifPresentOrElse(
                original -> {
                    OfficeCenter officeCenter = findOfficeCenterByOfficeId(id).get();
                    boolean removed = officeCenter.getOfficeList().remove(original);
                    updateOfficeCenter(officeCenter);
                    offices.remove(original);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The character with id \"%d\" does not exist", id));
                });
    }
}
