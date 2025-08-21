package za.ac.cput.repository;

/* DesignRepository.java
   Design Repository interface
   Author: Nompumezo Mcatshulwa (222614153) */

import za.ac.cput.domain.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DesignRepository extends JpaRepository<Design, Integer> {

    Design findByDesignId(int designId);

    List<Design> findByFilePathContaining(String filePath);

    boolean existsByDesignId(int designId);

    void deleteByDesignId(int designId);
}