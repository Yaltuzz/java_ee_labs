package pl.edu.pg.lab.office.service;

import lombok.NoArgsConstructor;
import pl.edu.pg.lab.office.entity.Office;
import pl.edu.pg.lab.office.repository.OfficeRepository;
import pl.edu.pg.lab.officeCenter.entity.OfficeCenter;
import pl.edu.pg.lab.officeCenter.repository.OfficeCenterRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@NoArgsConstructor
public class OfficeService {
    private OfficeRepository repository;
    private OfficeCenterRepository officeCenterRepository;

    @Inject
    public OfficeService(OfficeRepository repository,OfficeCenterRepository officeCenterRepository) {
        this.repository = repository;
        this.officeCenterRepository = officeCenterRepository;
    }

    @Transactional
    public void create(Office office) {
        repository.create(office);
    }

    public Optional<Office> find(int id) {
        return repository.find(id);
    }

    public List<Office> findAll() {
        return repository.findAll();
    }

    public Optional<List<Office>> findByOfficeCenter(int id) {
        Optional<OfficeCenter> officeCenter = officeCenterRepository.find(id);
        if (officeCenter.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(repository.findAllByOfficeCenter(officeCenter.get()));
        }
    }
    @Transactional
    public void delete(int id) {
        repository.delete(repository.find(id).orElseThrow());
    }

    @Transactional
    public void update(Office office) {
        repository.update(office);
    }
}
