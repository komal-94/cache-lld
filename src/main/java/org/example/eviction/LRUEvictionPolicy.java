package org.example.eviction;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
    private final LinkedHashMap<K, Boolean> accessOrder;

    public LRUEvictionPolicy() {
        this.accessOrder = new LinkedHashMap<K, Boolean>(16, 0.75f, true);
    }

    @Override
    public void keyAccessed(K key) {
        accessOrder.put(key, true);
    }

    @Override
    public K evictKey() {
        for (Map.Entry<K, Boolean> entry:accessOrder.entrySet()) {
            return entry.getKey();
        }
        return null;
    }

    @Override
    public void keyRemoved(K key) {
        accessOrder.remove(key);
    }
}
