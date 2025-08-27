package za.ac.cput.repository;

/* TShirtRepository.java
   TShirt Repository interface
   Author: Nompumezo Mcatshulwa (222614153) */

import za.ac.cput.domain.TShirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TShirtRepository extends JpaRepository<TShirt, Integer> {

    TShirt findByProductId(int productId);

    List<TShirt> findByName(String name);

    List<TShirt> findByColor(String color);

    List<TShirt> findBySize(String size);

    List<TShirt> findByPriceBetween(double minPrice, double maxPrice);

    List<TShirt> findByDesignId(int designId);

    List<TShirt> findByPlacementDataId(int placementDataId);

    boolean existsByProductId(int productId);

    void deleteByProductId(int productId);
}