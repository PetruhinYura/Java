package products;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class Product {
    private final String id;
    private final String name;
    private final double price;

    protected Product(final String id, final String name, final double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
