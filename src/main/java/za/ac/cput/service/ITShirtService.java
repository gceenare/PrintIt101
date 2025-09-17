package za.ac.cput.service;


import za.ac.cput.domain.TShirt;
import java.util.List;


public interface ITShirtService {
    TShirt create(TShirt tshirt);

    TShirt read(int productId);

    TShirt update(TShirt tshirt);

    boolean delete(int productId);

    List<TShirt> getAll();

    List<TShirt> findByName(String name);

    List<TShirt> findByColor(String color);

    List<TShirt> findBySize(String size);

    List<TShirt> findByPriceRange(double minPrice, double maxPrice);

    List<TShirt> findByDesignId(int designId);

    List<TShirt> findByPlacementDataId(int placementDataId);
}
