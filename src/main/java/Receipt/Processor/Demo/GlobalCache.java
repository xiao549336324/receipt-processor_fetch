package Receipt.Processor.Demo;

import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * GlobalCache serves as an in-memory storage for receipts and their corresponding points.
 * It uses thread-safe ConcurrentHashMap to ensure safe concurrent access.
 */
@Component
public class GlobalCache {

    // A thread-safe map to store receipt data, where the key is the receipt ID
    private final Map<String, ReceiptBean> receiptBeanCache = new ConcurrentHashMap<>();

    /**
     * Stores a receipt in the cache.
     * @param key The unique receipt ID.
     * @param value The ReceiptBean object containing receipt details.
     */
    public void receiptBeanCacheMapPut(String key, ReceiptBean value) {
        receiptBeanCache.put(key, value);
    }

    /**
     * Retrieves a receipt from the cache.
     * @param key The receipt ID.
     * @return The ReceiptBean associated with the given ID, or null if not found.
     */
    public ReceiptBean receiptBeanCacheMapGet(String key) {
        return receiptBeanCache.get(key);
    }

    /**
     * Removes a receipt from the cache.
     * @param key The receipt ID to be removed.
     */
    public void receiptBeanCacheMapRemove(String key) {
        receiptBeanCache.remove(key);
    }

    // A thread-safe map to store points associated with each receipt ID
    private final Map<String, Integer> pointsCache = new ConcurrentHashMap<>();

    /**
     * Stores the calculated points for a receipt in the cache.
     * @param key The receipt ID.
     * @param value The calculated points for the receipt.
     */
    public void pointsCacheMapPut(String key, Integer value) {
        pointsCache.put(key, value);
    }

    /**
     * Retrieves the points associated with a receipt.
     * @param key The receipt ID.
     * @return The points for the given receipt ID, or null if not found.
     */
    public Integer pointsCacheMapGet(String key) {
        return pointsCache.get(key);
    }

    /**
     * Removes the points entry associated with a receipt ID.
     * @param key The receipt ID to be removed.
     */
    public void pointsCacheMapRemove(String key) {
        pointsCache.remove(key);
    }
}