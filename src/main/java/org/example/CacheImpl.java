package org.example;

import org.example.customExceptions.KeyNotFoundException;
import org.example.customExceptions.StorageFullException;
import org.example.eviction.EvictionPolicy;
import org.example.storage.Storage;

public class CacheImpl<K, V> implements  Cache<K, V> {
    private final Storage<K, V> storage;
    private final EvictionPolicy<K> evictionPolicy;

    public CacheImpl(Storage<K, V> storage, EvictionPolicy<K> evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    @Override
    public void put(K key, V val) throws StorageFullException {
        if (storage.isFull()) {
            K evictedKey = evictionPolicy.evictKey();
            if (evictedKey != null) {
                storage.remove(evictedKey);
            } else {
                throw new StorageFullException("Storage is full");
            }
        }
        storage.put(key, val);
        evictionPolicy.keyAccessed(key);
    }

    @Override
    public V get(K key) throws KeyNotFoundException {
        V value = storage.get(key);
        if (value == null) {
            throw new KeyNotFoundException("KEY NOT FOUND:" + key);
        }
        evictionPolicy.keyAccessed(key);
        return value;
    }

    @Override
    public void delete(K key) throws KeyNotFoundException {
        if (!storage.containsKey(key)) {
            throw new KeyNotFoundException("KEY NOT FOUND:" + key);
        }
        storage.remove(key);
        evictionPolicy.keyRemoved(key);
    }
}
