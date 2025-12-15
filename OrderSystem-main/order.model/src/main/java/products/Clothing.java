package products;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Clothing extends Product {
    private final String size;
    private final String material;

    protected Clothing(
            final String id,
            final String name,
            final double price,
            final String size,
            final String material) {
        super(id, name, price);
        this.size = size;
        this.material = material;
    }
}
