package com.example.bags.service;

import com.example.bags.dao.BagRepository;
import com.example.bags.dao.DetailRepository;
import com.example.bags.exception.ServiceRuntimeException;
import com.example.bags.model.Bag;
import com.example.bags.model.BagPriceInfo;
import com.example.bags.model.Detail;
import com.example.bags.model.Entity.BagEntity;
import com.example.bags.model.Entity.DetailEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
            throw  new ServiceRuntimeException("need more info in bag: " + bag);

        var bagEntity = new BagEntity(bag);

        this.bagRepository.save(bagEntity);

        this.saveDetails(bag.getDetails(), bagEntity);

        bag.setId(bagEntity.getId());

        return bag;
    }

    private void saveDetails(List<Detail> details, BagEntity bagEntity) {
        if (details == null || details.isEmpty())
            throw new ServiceRuntimeException("no details is present");

        var detailsEntity = details.stream()
                .map(d -> {
                    if (!this.isCorrectDetail(d))
                        throw new ServiceRuntimeException("detail is not correct: " + d);

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
        || detail.getArea().compareTo(BigDecimal.ZERO) <= 0
        || detail.getLength().compareTo(BigDecimal.ZERO) < 0
        || detail.getWidth().compareTo(BigDecimal.ZERO) < 0)
            return false;

        return true;
    }

    public Bag getBagById(Integer id) {

        var entityBag = this.bagRepository.findById(id)
                .orElseThrow(() -> new ServiceRuntimeException("can not find Bag by id: " + id));

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
