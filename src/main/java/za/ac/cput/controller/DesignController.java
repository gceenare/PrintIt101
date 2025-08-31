package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Design;
import za.ac.cput.service.DesignService;

import java.util.List;

@RestController
@RequestMapping("/design")
public class DesignController {

    private DesignService service;

    @Autowired
    public DesignController(DesignService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Design create(@RequestBody Design design) {
        return service.create(design);
    }

    @GetMapping("/read/{id}")
    public Design read(@PathVariable Integer id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public Design update(@RequestBody Design design) {
        return service.update(design);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping("/getAll")
    public List<Design> getAll() {
        return service.getAll();
    }

    @GetMapping("/findByName/{name}")
    public List<Design> findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping("/findByCategory/{category}")
    public List<Design> findByCategory(@PathVariable String category) {
        return service.findByCategory(category);
    }

    @GetMapping("/findByDesigner/{designer}")
    public List<Design> findByDesigner(@PathVariable String designer) {
        return service.findByDesigner(designer);
    }
}