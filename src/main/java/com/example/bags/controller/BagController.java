package com.example.bags.controller;

import com.example.bags.model.Bag;
import com.example.bags.service.BagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BagController {

    private final BagService bagService;

    public BagController(BagService bagService) {
        this.bagService = bagService;
    }

    @PostMapping("api/v1/bag")
    public void insertBag(@RequestBody Bag bag) {
        this.bagService.insertBag(bag);
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
