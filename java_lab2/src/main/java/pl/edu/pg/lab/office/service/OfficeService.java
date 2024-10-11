package pl.edu.pg.lab.office.service;

import lombok.NoArgsConstructor;
import pl.edu.pg.lab.office.entity.Office;
import pl.edu.pg.lab.office.repository.OfficeRepository;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@NoArgsConstructor
public class OfficeService {
    private OfficeRepository repository;

    @Inject
    public OfficeService(OfficeRepository repository) {
        this.repository = repository;
    }

    public void create(Office office) {
        repository.create(office);
    }

    public Optional<Office> find(int id) {
        return repository.find(id);
    }

    public List<Office> findAll() {
        return repository.findAll();
    }
    public void delete(int id) {
        repository.delete(repository.find(id).orElseThrow());
    }
    public void update(Office office) {
        repository.update(office);
    }
}
