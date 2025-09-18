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
        // Saves the new design entity to the database.
        return repository.save(design);
    }

    @Override
    public Design read(int designId) {
        // Finds a design by its ID, returning null if not found.
        return repository.findById(designId).orElse(null);
    }

    @Override
    public Design update(Design design) {
        // Checks if the design exists before saving the updated version.
        if (repository.existsById(design.getDesignId())) {
            return repository.save(design);
        }
        return null;
    }

    @Override
    public boolean delete(int designId) {
        // Checks if the design exists before deleting it.
        if (repository.existsById(designId)) {
            repository.deleteById(designId);
            return true;
        }
        return false;
    }

    @Override
    public List<Design> getAll() {
        // Returns a list of all designs from the database.
        return repository.findAll();
    }

    @Override
    public List<Design> findByFilePath(String filePath) {
        // Finds designs where the file path contains the given string.
        return repository.findByFilePathContaining(filePath);
    }
}