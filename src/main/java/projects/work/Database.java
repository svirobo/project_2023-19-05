package projects.work;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * This should represent in-memory storage for any messages consumed by this program. Main index is based
 * on the type of the class that must implement {@link Identifiable}.
 *
 * @implNote This utilizes concurrent hash map for storing, so reads are only eventually consistent
 */
public final class Database {

    final ConcurrentMap<Class<? extends Identifiable>, ConcurrentMap<Number, Identifiable>> mainIndex = new ConcurrentHashMap<>();

    public void putItem(Class<? extends Identifiable> type, Identifiable item) {
        // cannot put null key into concurrent hash map
        if (type == null) {
            throw new NullPointerException("Type of the item must be defined");
        }
        ConcurrentMap<Number, Identifiable> entityIndex = mainIndex.get(type);
        if (entityIndex == null) {
            entityIndex = new ConcurrentHashMap<>();
            mainIndex.put(type, entityIndex);
        }
        entityIndex.put(item.identify(), item);
        System.out.println("Putting item");
    }

    public void printIndexValues(Class<? extends Identifiable> type) {
        final ConcurrentMap<Number, Identifiable> entityIndex = mainIndex.get(type);
        if (entityIndex == null || entityIndex.isEmpty()) {
            System.out.println("Empty index for: " + type);
            return;
        }
        System.out.println("Printing values:");
        entityIndex.forEach((key, value) -> System.out.println(value));
    }

    public void deleteIndex(Class<? extends Identifiable> type) {
        final ConcurrentMap<Number, Identifiable> entityIndex = mainIndex.get(type);
        if (entityIndex == null) {
            System.out.println("Empty index for: " + type);
            return;
        }
        entityIndex.keySet().clear();
        System.out.println("Index deleted");
    }
}
