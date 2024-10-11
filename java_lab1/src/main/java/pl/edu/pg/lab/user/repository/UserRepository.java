package pl.edu.pg.lab.user.repository;

import pl.edu.pg.lab.datastore.DataStore;
import pl.edu.pg.lab.user.entity.User;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Dependent
public class UserRepository {

    private DataStore store;

    @Inject
    public UserRepository(DataStore store) {
        this.store = store;
    }

    public Optional<User> find(String login) {
        return store.findUser(login);
    }

    public List<User> findAll() {
        return store.findAllUsers();
    }

    public void create(User entity) {
        store.createUser(entity);
    }
    public void update(User entity) {
        store.updateUser(entity);
    }


}
