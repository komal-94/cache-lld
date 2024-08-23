package org.example.storage;

import java.util.Map;

public abstract class Storage<K, V> {
    protected final int capacity;
    protected final Map<K, V> storage;

    public Storage(int capacity, Map<K, V> storage) {
        this.capacity = capacity;
        this.storage = storage;
    }

    public void put(K key, V val) {
        storage.put(key, val);
    }
    public V get(K key) {
        return storage.get(key);
    }
    public void remove(K key) {
        storage.remove(key);
    }
    public boolean isFull() {
        return storage.size() >= capacity;
    }
    public boolean containsKey(K key) {
        return storage.containsKey(key);
    }
}
