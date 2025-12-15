package repository;

import order.Order;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class OrderRepository {
    private final ConcurrentHashMap<String, Order<?>> storage = new ConcurrentHashMap<>();

    public void saveOrder(Order<?> product) {
        storage.put(product.getId(), product);
    }

    public Order<?> findProductById(final String id) {
        final var order = storage.get(id);
        if (order == null) {
            throw new OrderNotFoundException(id);
        }
        return order;
    }

    public Collection<Order<?>> findAllProducts() {
        return storage.values();
    }
}