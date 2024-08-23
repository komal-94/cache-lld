package org.example;

public interface Cache<K, V> {
    void put(K key, V val);
    V get(K key);
    void delete(K key);
}
