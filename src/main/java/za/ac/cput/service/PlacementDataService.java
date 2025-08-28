/* PlacementDataService.java
   PlacementData Service class
   Author: Siyabulela Mgijima (230680305)
   Date: 28 August 2025
*/
package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.PlacementData;
import za.ac.cput.repository.PlacementDataRepository;

import java.util.List;

@Service
public class PlacementDataService implements IPlacementDataService {

    private final PlacementDataRepository repository;

    @Autowired
    public PlacementDataService(PlacementDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public PlacementData create(PlacementData placementData) {
        return repository.save(placementData);
    }

    @Override
    public PlacementData read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public PlacementData update(PlacementData placementData) {
        return repository.save(placementData);
    }

    @Override
    public boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<PlacementData> getAll() {
        return repository.findAll();
    }

    @Override
    public List<PlacementData> findByPositionId(int positionId) {
        return repository.findByPosition_PositionId(positionId);
    }

    @Override
    public List<PlacementData> findByRotationId(int rotationId) {
        return repository.findByRotation_RotationId(rotationId);
    }

    @Override
    public List<PlacementData> findByScaleId(int scaleId) {
        return repository.findByScale_ScaleId(scaleId);
    }
}
