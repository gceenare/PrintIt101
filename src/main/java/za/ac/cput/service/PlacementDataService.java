/* PlacementDataService.java
   PlacementData Service class
   Author: Siyabulela Mgijima (230680305)
   Updated: 5 September 2025
*/
package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.PlacementData;
import za.ac.cput.domain.Position;
import za.ac.cput.domain.Rotation;
import za.ac.cput.domain.Scale;
import za.ac.cput.repository.PlacementDataRepository;
import za.ac.cput.repository.PositionRepository;
import za.ac.cput.repository.RotationRepository;
import za.ac.cput.repository.ScaleRepository;

import java.util.List;

@Service
public class PlacementDataService implements IPlacementDataService {

    private final PlacementDataRepository repository;
    private final PositionRepository positionRepository;
    private final RotationRepository rotationRepository;
    private final ScaleRepository scaleRepository;

    @Autowired
    public PlacementDataService(PlacementDataRepository repository,
                                PositionRepository positionRepository,
                                RotationRepository rotationRepository,
                                ScaleRepository scaleRepository) {
        this.repository = repository;
        this.positionRepository = positionRepository;
        this.rotationRepository = rotationRepository;
        this.scaleRepository = scaleRepository;
    }

    @Override
    public PlacementData create(PlacementData placementData) {
        // Fetch existing entities
        Position existingPosition = positionRepository.findById(
                placementData.getPosition().getPositionId()
        ).orElseThrow(() -> new RuntimeException("Position not found"));

        Rotation existingRotation = rotationRepository.findById(
                placementData.getRotation().getRotationId()
        ).orElseThrow(() -> new RuntimeException("Rotation not found"));

        Scale existingScale = scaleRepository.findById(
                placementData.getScale().getScaleId()
        ).orElseThrow(() -> new RuntimeException("Scale not found"));

        // Rebuild PlacementData using builder
        PlacementData managed = new PlacementData.Builder()
                .setPosition(existingPosition)
                .setRotation(existingRotation)
                .setScale(existingScale)
                .build();

        return repository.save(managed);
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