package products;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Electronics extends Product {
    private final String appliance;
    private final String model;

    protected Electronics(
            final String id,
            final String name,
            final double price,
            final String appliance,
            final String model) {
        super(id, name, price);
        this.appliance = appliance;
        this.model = model;
    }
}
