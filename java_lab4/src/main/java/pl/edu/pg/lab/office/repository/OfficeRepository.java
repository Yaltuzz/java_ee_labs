package pl.edu.pg.lab.office.repository;

import pl.edu.pg.lab.datastore.DataStore;
import pl.edu.pg.lab.office.entity.Office;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.user.entity.User;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class OfficeRepository {


    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Optional<Office> find(int id) {
        return Optional.ofNullable(em.find(Office.class, id));
    }

    public List<Office> findAll() {
        return em.createQuery("select office from Office office", Office.class).getResultList();
    }

    public List<Office> findAllByOfficeCenter(OfficeCenter officeCenter) {
        return em.createQuery("select c from Office c where c.officeCenter = :officeCenter", Office.class)
                .setParameter("officeCenter", officeCenter)
                .getResultList();
    }

    public void create(Office entity) {
        em.persist(entity);
    }
    public void update(Office entity) {
        em.merge(entity);
    }
    public void delete(Office entity) {
        em.remove(em.find(Office.class, entity.getId()));
    }


}
