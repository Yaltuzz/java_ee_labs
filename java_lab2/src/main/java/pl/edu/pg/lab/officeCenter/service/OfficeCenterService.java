package pl.edu.pg.lab.officeCenter.service;

import lombok.NoArgsConstructor;
import pl.edu.pg.lab.office.entity.Office;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.officeCenter.repository.OfficeCenterRepository;
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
public class OfficeCenterService {
    private OfficeCenterRepository repository;

    @Inject
    public OfficeCenterService(OfficeCenterRepository repository) {
        this.repository = repository;
    }

    public void create(OfficeCenter officeCenter) {
        repository.create(officeCenter);
    }

    public Optional<OfficeCenter> find(int id) {
        return repository.find(id);
    }

    public List<OfficeCenter> findAll() {
        return repository.findAll();
    }
    public void delete(int id) {
        repository.delete(repository.find(id).orElseThrow());
    }
    public void update(OfficeCenter officeCenter) {
        repository.update(officeCenter);
    }




}
