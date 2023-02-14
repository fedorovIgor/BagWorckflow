package com.example.bags.service;

import com.example.bags.dao.BagRepository;
import com.example.bags.dao.DetailRepository;
import com.example.bags.model.Bag;
import com.example.bags.model.Detail;
import com.example.bags.model.Entity.BagEntity;
import com.example.bags.model.Entity.DetailEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BagService {

    private final BagRepository bagRepository;
    private final DetailRepository detailRepository;

    public BagService(BagRepository bagRepository, DetailRepository detailRepository) {
        this.bagRepository = bagRepository;
        this.detailRepository = detailRepository;
    }

    @Transactional
    public Bag insertBag(Bag bag) {

        if (bag.getName() == "" || bag.getVendorCode() == null)
            throw  new RuntimeException("need more info " + bag);

        var bagEntity = new BagEntity(bag);

        this.bagRepository.save(bagEntity);

        this.saveDetails(bag.getDetails(), bagEntity);

        bag.setId(bagEntity.getId());

        return bag;
    }

    private void saveDetails(List<Detail> details, BagEntity bagEntity) {
        if (details == null || details.isEmpty())
            throw new RuntimeException("no details is present");

        var detailsEntity = details.stream()
                .map(d -> {
                    if (!this.isCorrectDetail(d))
                        throw new RuntimeException("detail is not correct: " + d);

                    var e = new DetailEntity(d);
                    e.setBag(bagEntity);

                    return e;
                })
                .toList();

        this.detailRepository.saveAll(detailsEntity);
    }

    private boolean isCorrectDetail(Detail detail) {
        if ( detail.getCount() <= 0
        || detail.getName() == null || detail.getName().isEmpty()
        || detail.getArea() <= 0
        || detail.getLength() <= 0
        || detail.getWidth() <= 0)
            return false;

        return true;
    }

    public Bag getBagById(Integer id) {

        var entityBag = this.bagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("can not find by id: " + id));

        var bag = new Bag(entityBag);

        var detailsEntity = entityBag.getDetails();

        if (detailsEntity.size() == 0)
            return bag;

        var details = detailsEntity.stream()
                .map(e -> new Detail(e))
                .toList();

        bag.setDetails(details);

        return bag;
    }

    public List<Bag> getAllBags() {

        var bags =  new ArrayList<Bag>();

        bagRepository.findAll().forEach(e -> {
            var m = new Bag(e);

            var details = e.getDetails().stream()
                    .map(d -> new Detail(d))
                    .toList();

            m.setDetails(details);

            bags.add(m);
        });

        return bags;
    }
}
