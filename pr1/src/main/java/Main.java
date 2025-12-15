import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор",
                electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю",
                smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);

        Cart cart = new Cart();
        List<Order> orderHistory = new ArrayList<>();

        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Зробити замовлення");
            System.out.println("5 - Видалити товар з кошика");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товару");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(product1);
                    System.out.println(product2);
                    System.out.println(product3);
                    break;
                case 2:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int idToAdd = scanner.nextInt();
                    if (idToAdd == 1)
                        cart.addProduct(product1);
                    else if (idToAdd == 2)
                        cart.addProduct(product2);
                    else if (idToAdd == 3)
                        cart.addProduct(product3);
                    else
                        System.out.println("Товар з таким ID не знайдено");
                    break;
                case 3:
                    System.out.println(cart);
                    break;
                case 4:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.add(order);
                        System.out.println("Замовлення оформлено!");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;
                case 5:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній");
                    } else {
                        System.out.println("Введіть ID товару для видалення з кошика:");
                        int idToRemove = scanner.nextInt();
                        boolean removed = cart.removeProduct(idToRemove);
                        if (removed) {
                            System.out.println("Товар видалено з кошика");
                        } else
                            System.out.println("Товар з таким ID не знайдено в кошику");
                    }
                    break;
                case 6:
                    System.out.println("Історія замовлень:");
                    if (orderHistory.isEmpty()) {
                        System.out.println("немає замовлень");
                    } else {
                        for (Order order : orderHistory) {
                            System.out.println(order);
                        }
                    }
                    break;
                case 7:
                    scanner.nextLine();
                    System.out.println("Введіть назву товару або категорію для пошуку:");
                    String search = scanner.nextLine().toLowerCase();

                    List<Product> allProducts = Arrays.asList(product1, product2, product3);

                    List<Product> results = new ArrayList<>();
                    for (Product product : allProducts) {
                        String name = product.getName().toLowerCase();
                        String category = product.getCategory().getName().toLowerCase();

                        if (name.contains(search) || category.contains(search)) {
                            results.add(product);
                        }
                    }

                    if (results.isEmpty()) {
                        System.out.println("Нічого не знайдено");
                    } else {
                        System.out.println("Знайдені товари:");
                        for (Product product : results) {
                            System.out.println(product);
                        }
                    }
                    break;
                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;
                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
}