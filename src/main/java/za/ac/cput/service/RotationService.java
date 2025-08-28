/* RotationService.java
   Rotation Service
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025
*/
package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Rotation;
import za.ac.cput.repository.RotationRepository;

import java.util.List;

@Service
public class RotationService implements IRotationService {

    private final RotationRepository repository;

    @Autowired
    public RotationService(RotationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Rotation create(Rotation rotation) {
        return repository.save(rotation);
    }

    @Override
    public Rotation read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Rotation update(Rotation rotation) {
        return repository.save(rotation);
    }

    @Override
    public boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Rotation> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Rotation> findByX(double x) {
        return repository.findByX(x);
    }

    @Override
    public List<Rotation> findByY(double y) {
        return repository.findByY(y);
    }

    @Override
    public List<Rotation> findByZ(double z) {
        return repository.findByZ(z);
    }
}
