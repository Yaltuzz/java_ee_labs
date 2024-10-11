package pl.edu.pg.lab.officeCenter.repository;

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
public class OfficeCenterRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Optional<OfficeCenter> find(int id) {
        return Optional.ofNullable(em.find(OfficeCenter.class, id));
    }

    public List<OfficeCenter> findAll() {
        return em.createQuery("select officeCenter from OfficeCenter officeCenter", OfficeCenter.class).getResultList();
    }

    public void create(OfficeCenter entity) {
        em.persist(entity);
    }
    public void update(OfficeCenter entity) {
        em.merge(entity);
    }
    public void delete(OfficeCenter entity) {
        em.remove(entity);
    }
}
