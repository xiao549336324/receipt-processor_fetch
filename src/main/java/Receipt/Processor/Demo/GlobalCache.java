package Receipt.Processor.Demo;

import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Component
public class GlobalCache {
    private final Map<String, ReceiptBean> receiptBeanCache = new ConcurrentHashMap<>();

    public void receiptBeanCacheMapPut(String key, ReceiptBean value) {
        receiptBeanCache.put(key, value);
    }

    public ReceiptBean receiptBeanCacheMapGet(String key) {
        return receiptBeanCache.get(key);
    }

    public void receiptBeanCacheMapRemove(String key) {
        receiptBeanCache.remove(key);
    }

    private final Map<String, Integer> pointsCache = new ConcurrentHashMap<>();

    public void pointsCacheMapPut(String key, Integer value) {
        pointsCache.put(key, value);
    }

    public Integer pointsCacheMapGet(String key) {
        return pointsCache.get(key);
    }

    public void pointsCacheMapRemove(String key) {
        pointsCache.remove(key);
    }
}