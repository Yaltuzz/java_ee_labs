package pl.edu.pg.lab.user.service;

import lombok.NoArgsConstructor;
import pl.edu.pg.lab.user.entity.User;
import pl.edu.pg.lab.user.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
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

    @Transactional
    public void create(User user) {
        repository.create(user);
    }

    public Optional<User> find(String login) {
        return repository.find(login);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

}
