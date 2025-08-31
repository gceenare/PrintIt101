package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.TShirt;
import za.ac.cput.repository.TShirtRepository;

import java.util.List;

@Service
public class TShirtService implements ITShirtService {

    private TShirtRepository repository;

    @Autowired
    public TShirtService(TShirtRepository repository) {
        this.repository = repository;
    }

    @Override
    public TShirt create(TShirt tshirt) {
        return repository.save(tshirt);
    }

    @Override
    public TShirt read(int productId) {
        return repository.findById(productId).orElse(null);
    }

    @Override
    public TShirt update(TShirt tshirt) {
        return repository.save(tshirt);
    }

    @Override
    public boolean delete(int productId) {
        if(repository.existsByProductId(productId)) {
            repository.deleteByProductId(productId);
            return true;
        }
        return false;
    }

    @Override
    public List<TShirt> getAll() {
        return repository.findAll();
    }

    @Override
    public List<TShirt> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<TShirt> findByColor(String color) {
        return repository.findByColor(color);
    }

    @Override
    public List<TShirt> findBySize(String size) {
        return repository.findBySize(size);
    }

    @Override
    public List<TShirt> findByPriceRange(double minPrice, double maxPrice) {
        return repository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<TShirt> findByDesignId(int designId) {
        return repository.findByDesignId(designId);
    }

    @Override
    public List<TShirt> findByPlacementDataId(int placementDataId) {
        return repository.findByPlacementDataId(placementDataId);
    }
}
