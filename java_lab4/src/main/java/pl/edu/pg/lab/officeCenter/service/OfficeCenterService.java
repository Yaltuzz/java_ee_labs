package pl.edu.pg.lab.officeCenter.service;

import lombok.NoArgsConstructor;
import pl.edu.pg.lab.office.entity.Office;
import pl.edu.pg.lab.office.service.OfficeService;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.officeCenter.repository.OfficeCenterRepository;
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
public class OfficeCenterService {
    private OfficeCenterRepository repository;

    private OfficeService officeService;

    @Inject
    public OfficeCenterService(OfficeCenterRepository repository,OfficeService officeService) {
        this.repository = repository;
        this.officeService = officeService;
    }

    @Transactional
    public void create(OfficeCenter officeCenter) {
        repository.create(officeCenter);
    }

    public Optional<OfficeCenter> find(int id) {
        return repository.find(id);
    }

    public List<OfficeCenter> findAll() {
        return repository.findAll();
    }
    @Transactional
    public void delete(int id) {
        repository.delete(repository.find(id).orElseThrow());
    }
    @Transactional
    public void update(OfficeCenter officeCenter) {
        repository.update(officeCenter);
    }
}
