package process;

import order.Order;

public record OrderProcessor(Order<?> order) {

    public void processOrder() {
        System.out.printf(
                "Starting processing of the order [%s] for product [%s]...%n",
                order.getId(), order.getProduct().getId()
        );

        try {
            Thread.sleep(500L);
        } catch (final InterruptedException e) {
            System.err.printf("Order processing was interrupted: %s%n",
                    e.getMessage()
            );
        }

        System.out.printf("Processing finished of the order [%s] for product [%s]%n",
                order.getId(), order.getProduct().getId()
        );
    }
}
