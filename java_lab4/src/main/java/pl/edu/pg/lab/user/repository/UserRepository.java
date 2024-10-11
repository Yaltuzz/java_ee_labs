package pl.edu.pg.lab.user.repository;

import pl.edu.pg.lab.datastore.DataStore;
import pl.edu.pg.lab.office.entity.Office;
import pl.edu.pg.lab.user.entity.User;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class UserRepository {


    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Optional<User> find(String login) {

        return Optional.ofNullable(em.find(User.class, login));
    }

    public List<User> findAll() {
        return em.createQuery("select user from User user", User.class).getResultList();
    }

    public void create(User entity) {
        em.persist(entity);
    }
    public void update(User entity) {
        em.merge(entity);
    }


}
