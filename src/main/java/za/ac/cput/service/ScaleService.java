/* ScaleService.java
   Scale Service
   Author: Siyabulela Mgijima (230680305)
   Updated: 14 September 2025
*/
package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Scale;
import za.ac.cput.repository.ScaleRepository;

import java.util.List;

@Service
public class ScaleService implements IScaleService {

    private final ScaleRepository repository;

    @Autowired
    public ScaleService(ScaleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Scale create(Scale scale) {
        return repository.save(scale);
    }

    @Override
    public Scale read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Scale update(Scale scale) {
        return repository.save(scale);
    }

    @Override
    public boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Scale> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Scale> findByValue(double value) {
        return repository.findByValue(value);
    }
}
