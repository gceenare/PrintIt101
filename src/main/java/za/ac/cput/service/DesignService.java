package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Design;
import za.ac.cput.repository.DesignRepository;
import java.util.List;

@Service
public class DesignService implements IDesignService {

    private DesignRepository repository;

    @Autowired
    public DesignService(DesignRepository repository) {
        this.repository = repository;
    }

    @Override
    public Design create(Design design) {
        return null;
    }

    @Override
    public Design read(int designId) {
        return null;
    }

    @Override
    public Design update(Design design) {
        return null;
    }

    @Override
    public boolean delete(int designId) {
        return false;
    }

    @Override
    public List<Design> getAll() {
        return List.of();
    }

    @Override
    public List<Design> findByFilePath(String filePath) {
        return List.of();
    }
}
