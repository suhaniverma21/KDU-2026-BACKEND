package com.assessment.quickShip.service;

import com.assessment.quickShip.entity.Package;
import com.assessment.quickShip.repository.PackageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class PackageService {
    private final PackageRepository packageRepository;
    private final ExecutorService executorService;
    private static final Logger logger =
            LoggerFactory.getLogger(PackageService.class);

    public PackageService(PackageRepository packageRepository, ExecutorService executorService) {
        this.packageRepository = packageRepository;
        this.executorService = executorService;
    }

    public Package addPackage(Package pck) {

        if (pck.getDestination() == null || pck.getDestination().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        pck.setStatus("PENDING");
        Package savedPackage = packageRepository.save(pck);
        String packageId = savedPackage.getId();
        executorService.submit(() -> {
            try {
                logger.info("Background thread started for package {}", savedPackage.getId());
                Thread.sleep(3000);

                // FIND inside background thread
                Package foundPackage = packageRepository
                        .findById(packageId)
                        .orElseThrow();

                foundPackage.setStatus("SORTED");
                packageRepository.save(foundPackage);
                logger.info("Background thread finished for package {}", foundPackage.getId());

            } catch (Exception e) {
                packageRepository.findById(packageId).ifPresent(b -> {
                    b.setStatus("FAILED");
                    packageRepository.save(b);
                });
            }
        });
        // Save once
        return savedPackage;
    }


    public Double getRevenue() {

        /*List<Package> packages=packageRepository.findAll();
        Double revenue = 0.0;
        for(int i=0;i<packages.toArray().length;i++)
        {
            if(Objects.equals(packages.get(i).getStatus(), "SORTED"))
            {
                revenue+=packages.get(i).getWeight()*2.5;
            }
        }
        */
        /*return packageRepository.findAll().stream().collect(Collectors.groupingBy(
                Package::getStatus,
                Collectors.counting()
        ));*/
        //return 5.0;
        return packageRepository.findAll()
                .stream()
                .filter(p-> Objects.equals(p.getStatus(), "SORTED"))
                .map(p->p.getWeight()*2.5)
                .reduce(0.0, Double::sum);
    }

    public Optional<Package> getPackageById(String id) {
        return packageRepository.findById(id);
    }


    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }


    /*public void deleteBook(String id){
        Package existingBook = packageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        packageRepository.deleteById(id);

    }*/
}
