package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Design;
import za.ac.cput.repository.DesignRepository;
import java.util.List;

@Service
public class DesignService implements IDesignService {

    private final DesignRepository repository;

    @Autowired
    public DesignService(DesignRepository repository) {
        this.repository = repository;
    }

    @Override
    public Design create(Design design) {

        return repository.save(design);
    }

    @Override
    public Design read(int designId) {

        return repository.findById(designId).orElse(null);
    }

    @Override
    public Design update(Design design) {

        if (repository.existsById(design.getDesignId())) {
            return repository.save(design);
        }
        return null;
    }

    @Override
    public boolean delete(int designId) {

        if (repository.existsById(designId)) {
            repository.deleteById(designId);
            return true;
        }
        return false;
    }

    @Override
    public List<Design> getAll() {

        return repository.findAll();
    }

    @Override
    public List<Design> findByFilePath(String filePath) {

        return repository.findByFilePathContaining(filePath);
    }
}