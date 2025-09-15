/* RotationService.java
   Rotation Service
   Author: Siyabulela Mgijima (230680305)
   Updated: 14 September 2025
*/
package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Rotation;
import za.ac.cput.repository.RotationRepository;
import za.ac.cput.util.Helper;

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
        if (rotation == null || !Helper.isAngleValid(rotation.getAngle())) {
            throw new IllegalArgumentException("Invalid rotation angle: " + (rotation != null ? rotation.getAngle() : "null"));
        }
        return repository.save(rotation);
    }

    @Override
    public Rotation read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Rotation update(Rotation rotation) {
        if (rotation == null || !Helper.isAngleValid(rotation.getAngle())) {
            throw new IllegalArgumentException("Invalid rotation angle: " + (rotation != null ? rotation.getAngle() : "null"));
        }
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
    public List<Rotation> findByAngle(double angle) {
        return repository.findByAngle(angle);
    }
}
