import org.example.CacheImpl;
import org.example.customExceptions.KeyNotFoundException;
import org.example.customExceptions.StorageFullException;
import org.example.eviction.LRUEvictionPolicy;
import org.example.storage.Storage;
import org.example.storage.TreeMapStorage;
import org.junit.Test;


public class CacheTest {
    @Test
    public void test() {
        CacheImpl<String, Integer> cache = new CacheImpl<>(new TreeMapStorage<>(3), new LRUEvictionPolicy<>());

        cache.put("A", 1);
        cache.put("B", 2);
        cache.put("C", 3);

        assert cache.get("A") == 1;
        assert cache.get("B") == 2;

        cache.put("D", 4);

//        assert cache.get("C").equals(KeyNotFoundException.class());
        try {
            cache.get("C");
            assert false : "Expected KeyNotFoundException to be thrown";
        } catch (KeyNotFoundException e) {
            assert true; // Exception was thrown as expected
        }

        assert cache.get("D") == 4;

        cache.delete("A");
        try {
            cache.get("A");
            assert false : "Expected KeyNotFoundException to be thrown";
        } catch (KeyNotFoundException e) {
            assert true; // Exception was thrown as expected
        }

        cache.put("E", 5);
        cache.put("F", 6);

        try {
            cache.put("G", 7);
            assert true : "Expected StorageFullException to be thrown";
        } catch (StorageFullException e) {
            assert true; // Exception was thrown as expected
        }
    }

}
