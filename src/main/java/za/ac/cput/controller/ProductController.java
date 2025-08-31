package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Product;
import za.ac.cput.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }

    @GetMapping("/read/{id}")
    public Product read(@PathVariable Integer id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public Product update(@RequestBody Product product) {
        return service.update(product);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping("/getAll")
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/findByName/{name}")
    public List<Product> findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping("/findByCategory/{category}")
    public List<Product> findByCategory(@PathVariable String category) {
        return service.findByCategory(category);
    }

    @GetMapping("/findByPriceRange/{minPrice}/{maxPrice}")
    public List<Product> findByPriceRange(@PathVariable double minPrice, @PathVariable double maxPrice) {
        return service.findByPriceRange(minPrice, maxPrice);
    }
}