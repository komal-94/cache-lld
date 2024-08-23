package org.example;

import org.example.customExceptions.KeyNotFoundException;
import org.example.customExceptions.StorageFullException;
import org.example.eviction.LRUEvictionPolicy;
import org.example.storage.TreeMapStorage;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");

        try {
            Cache<String, Integer> cache = new CacheImpl<>(
                    new TreeMapStorage<>(3),  // Using TreeMapStorage with a capacity of 3
                    new LRUEvictionPolicy<>()
            );

            System.out.println("Adding A -> 1");
            cache.put("A", 1);
            System.out.println("Adding B -> 2");
            cache.put("B", 2);
            System.out.println("Adding C -> 3");
            cache.put("C", 3);

            System.out.println("Value for key A: " + cache.get("A"));
            System.out.println("Value for key B: " + cache.get("B"));

            System.out.println("Adding D -> 4 (This should evict the least recently used item)");
            cache.put("D", 4);

            try {
                System.out.println("Trying to get value for key C (should be evicted): " + cache.get("C"));
            } catch (KeyNotFoundException e) {
                System.out.println("KeyNotFoundException: " + e.getMessage());
            }

            System.out.println("Value for key D: " + cache.get("D"));

            System.out.println("Deleting key A");
            cache.delete("A");

            try {
                System.out.println("Trying to get value for key A (should be deleted): " + cache.get("A"));
            } catch (KeyNotFoundException e) {
                System.out.println("KeyNotFoundException: " + e.getMessage());
            }

            System.out.println("Adding E -> 5");
            cache.put("E", 5);
            System.out.println("Adding F -> 6");
            cache.put("F", 6);

            try {
                System.out.println("Adding G -> 7 (should throw StorageFullException)" );
                cache.put("G", 7);
            } catch (StorageFullException e) {
                System.out.println("StorageFullException: " + e.getMessage());
            }

        } catch (StorageFullException | KeyNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}