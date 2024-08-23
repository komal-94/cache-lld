package org.example.eviction;

public interface EvictionPolicy<K> {
    void keyAccessed(K key);
    K evictKey();
    void keyRemoved(K key);
}
