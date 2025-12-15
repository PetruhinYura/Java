package factory;

import com.github.javafaker.Faker;
import products.Clothing;
import products.Electronics;

public class ProductFactory {
    private static final Faker faker = new Faker();

    public static Electronics createElectronics() {
        final var priceString = faker.commerce().price().replace(',', '.');

        return Electronics.builder()
                .id(faker.idNumber().valid())
                .name(faker.commerce().productName())
                .price(Double.parseDouble(priceString))
                .appliance(faker.options().option("TV", "Laptop", "Phone"))
                .model(faker.commerce().promotionCode())
                .build();
    }

    public static Clothing createClothing() {
        final var priceString = faker.commerce().price().replace(',', '.');

        return Clothing.builder()
                .id(faker.idNumber().valid())
                .name(faker.commerce().productName())
                .price(Double.parseDouble(priceString))
                .size(faker.options().option("S", "M", "L", "XL"))
                .material(faker.commerce().material())
                .build();
    }
}
