package pl.edu.pg.lab.officeCenter.repository;

import pl.edu.pg.lab.datastore.DataStore;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.user.entity.User;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Dependent
public class OfficeCenterRepository {

    private DataStore store;

    @Inject
    public OfficeCenterRepository(DataStore store) {
        this.store = store;
    }

    public Optional<OfficeCenter> find(int id) {
        return store.findOfficeCenter(id);
    }

    public List<OfficeCenter> findAll() {
        return store.findAllOfficeCenters();
    }

    public void create(OfficeCenter entity) {
        store.createOfficeCenter(entity);
    }
    public void update(OfficeCenter entity) {
        store.updateOfficeCenter(entity);
    }
    public void delete(OfficeCenter entity) {
        store.deleteOfficeCenter(entity.getId());
    }



}
