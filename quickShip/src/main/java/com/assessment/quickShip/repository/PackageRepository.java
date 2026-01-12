package com.assessment.quickShip.repository;

import com.assessment.quickShip.entity.Package;
import org.springframework.stereotype.Repository;

import java.util.*;

import static java.util.Optional.ofNullable;
@Repository
public class PackageRepository {
    private final Map<String, Package> packageStore = new HashMap<>();
    private Integer idCounter = 1;

    public Package save(Package p) {

        if(p.getId()==null)
        {
            String id=Integer.toString(idCounter);
            idCounter++;

            p.setId(id);
        }
        packageStore.put(p.getId(), p);
        return p;
    }

    //Optional coz there may or may not be a package
    public Optional<Package> findById(String id) {

        return ofNullable(packageStore.get(id));
    }
    public List<Package> findAll() {
        return new ArrayList<>(packageStore.values());
    }
    public Package update(String id,Package p) {

        packageStore.put(id, p);
        return p;
    }

}
