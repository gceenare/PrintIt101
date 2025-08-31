package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.TShirt;
import za.ac.cput.service.TShirtService;

import java.util.List;

@RestController
@RequestMapping("/tshirt")
public class TShirtController {

    private TShirtService service;

    @Autowired
    public TShirtController(TShirtService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public TShirt create(@RequestBody TShirt tshirt) {
        return service.create(tshirt);
    }

    @GetMapping("/read/{id}")
    public TShirt read(@PathVariable Integer id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public TShirt update(@RequestBody TShirt tshirt) {
        return service.update(tshirt);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping("/getAll")
    public List<TShirt> getAll() {
        return service.getAll();
    }

    @GetMapping("/findBySize/{size}")
    public List<TShirt> findBySize(@PathVariable String size) {
        return service.findBySize(size);
    }

    @GetMapping("/findByColor/{color}")
    public List<TShirt> findByColor(@PathVariable String color) {
        return service.findByColor(color);
    }

}
