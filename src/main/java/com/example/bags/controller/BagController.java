package com.example.bags.controller;

import com.example.bags.model.Bag;
import com.example.bags.service.BagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class BagController {

    private final BagService bagService;

    public BagController(BagService bagService) {
        this.bagService = bagService;
    }

    @PostMapping("api/v1/bag")
    public Bag insertBag(@RequestBody Bag bag) {
        return this.bagService.insertBag(bag);
    }

    @GetMapping("api/v1/bag/{id}")
    public Bag getBag(@PathVariable Integer id) {
        return this.bagService.getBagById(id);
    }

    @GetMapping("api/v1/bag")
    public List<Bag> getAllBags() {
        return this.bagService.getAllBags();
    }
}
