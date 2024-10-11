package pl.edu.pg.lab.office.repository;

import pl.edu.pg.lab.datastore.DataStore;
import pl.edu.pg.lab.office.entity.Office;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.user.entity.User;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Dependent
public class OfficeRepository {

    private DataStore store;

    @Inject
    public OfficeRepository(DataStore store) {
        this.store = store;
    }

    public Optional<Office> find(int id) {
        return store.findOffice(id);
    }

    public List<Office> findAll() {
        return store.findAllOffices();
    }

    public List<Office> findAllByOfficeCenter(int id) {
        return store.findAllOfficesByOfficeCenter(id);
    }

    public void create(Office entity) {
        store.createOffice(entity);
    }
    public void update(Office entity) {
        store.updateOffice(entity);
    }
    public void delete(Office entity) {
        System.out.println(entity.getId());
        store.deleteOffice(entity.getId());
    }


}
