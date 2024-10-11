package pl.edu.pg.lab.user.service;

import lombok.NoArgsConstructor;
import pl.edu.pg.lab.user.entity.User;
import pl.edu.pg.lab.user.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@NoArgsConstructor
public class UserService {

    private UserRepository repository;

    @Inject
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void create(User user) {
        repository.create(user);
    }

    public Optional<User> find(String login) {
        return repository.find(login);
    }

    public List<User> findAll() {
        return repository.findAll();
    }
    public void updatePortrait(String login, InputStream is) {
        repository.find(login).ifPresent(user -> {
            try {
                System.out.println("present");
                user.setPortrait(is.readAllBytes());
                repository.update(user);
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }

    public void deletePortrait(String login) {
        byte[] arr = {};
        repository.find(login).ifPresent(user -> {
            user.setPortrait(arr);
            repository.update(user);
        });
    }
}
