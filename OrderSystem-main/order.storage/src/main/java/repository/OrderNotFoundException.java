package repository;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(final String id) {
        super("Order not found for id: " + id);
    }
}
