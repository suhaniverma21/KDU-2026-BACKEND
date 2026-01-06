package org.example;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.Inventory.findItem;

public class Main_Warehouse {
    public static void main(String[] args) {

        Inventory inv1 = new Inventory(
                "Main Inventory",
                List.of(
                        List.of("P10", "P20"),
                        List.of("P30", "P40")
                )
        );
        Inventory inv2 = new Inventory(
                "Backup Inventory",
                List.of(
                        List.of("X10", "X11"),
                        List.of("Y20")
                )
        );
        Inventory inv3 = new Inventory(
                "Warehouse Inventory",
                List.of(
                        List.of("W1", "W2", "W3")
                )
        );

        List<Inventory> all = List.of(inv1, inv2, inv3);
        Set<String> set = all.stream()
                .flatMap(inv -> inv.getPalletItemIds().stream())      // Stream<List<String>>
                .flatMap(pallet -> pallet.stream())
                .collect(Collectors.toSet());
        System.out.println(set);


        // Use orElse to provide a default if item is not found
        Inventory result = findItem("B200") // ID that doesn’t exist
                .orElse(new Inventory("Default Inventory", List.of()));

        System.out.println("Inventory name: " + result.getName());
        System.out.println("Inventory items: " + result.getItems());


        Optional<Inventory> r = Inventory.findItem("B200");
        if (result.isPresent()) {
            System.out.println("Inventory name: " + result.get().getName());
        } else {
            ItemPlaceholder placeholder = new ItemPlaceholder();
            System.out.println(placeholder.getInfo());
        }


    }
}
