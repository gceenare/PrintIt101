package za.ac.cput.service;

import za.ac.cput.domain.Design;
import java.util.List;

public interface IDesignService {
    Design create(Design design);

    Design read(int designId);

    Design update(Design design);

    boolean delete(int designId);

    List<Design> getAll();

    List<Design> findByFilePath(String filePath);
}
