/* PositionService.java
   Position Service
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025 */
package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Position;
import za.ac.cput.repository.PositionRepository;

import java.util.List;

@Service
public class PositionService implements IPositionService {

    private final PositionRepository repository;

    @Autowired
    public PositionService(PositionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Position create(Position position) {
        return repository.save(position);
    }

    @Override
    public Position read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Position update(Position position) {
        return repository.save(position);
    }

    @Override
    public boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Position> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Position> findByX(double x) {
        return repository.findByX(x);
    }

    @Override
    public List<Position> findByY(double y) {
        return repository.findByY(y);
    }

    @Override
    public List<Position> findByZ(double z) {
        return repository.findByZ(z);
    }
}

