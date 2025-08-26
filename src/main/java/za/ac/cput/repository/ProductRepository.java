package za.ac.cput.repository;

/* ProductRepository.java
   Product Repository interface
   Author: Nompumezo Mcatshulwa (222614153)
   Date: 11 May 2025 */

import za.ac.cput.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByProductId(int productId);

    List<Product> findByDesignId(int designId);

    List<Product> findByPlacementDataId(int placementDataId);

    List<Product> findByProductType(String productType);

    List<Product> findByDesignIdAndProductType(int designId, String productType);

    boolean existsByProductId(int productId);

    void deleteByProductId(int productId);
}