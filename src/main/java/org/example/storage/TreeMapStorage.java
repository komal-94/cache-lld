package org.example.storage;

import java.util.TreeMap;

public class TreeMapStorage<K, V> extends Storage<K, V> {
    public TreeMapStorage(int capacity) {
        super(capacity, new TreeMap<>());
    }
}
