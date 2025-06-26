package state;

import state.domain.Order;
import state.domain.OrderItem;
import state.domain.Product;

import java.math.BigDecimal;

public class Application {
    public static void main(String[] args) {
        // Create a new order
        Order order = new Order();
        var prod1 = new Product(1, "Rice", "Rice", BigDecimal.valueOf(10.24));
        var prod2 = new Product(2, "Beans", "Beans", BigDecimal.valueOf(8.40));
        var prod3 = new Product(3, "Meat", "Meat", BigDecimal.valueOf(47.89));
        var prod4 = new Product(3, "Meat", "Meat", BigDecimal.valueOf(47.89));
        order.addItem(
                new OrderItem(prod1, BigDecimal.valueOf(2))
        );
        order.addItem(
                new OrderItem(prod2, BigDecimal.valueOf(1))
        );
        order.addItem(
                new OrderItem(prod3, BigDecimal.valueOf(1))
        );
        order.addItem(
                new OrderItem(prod4, BigDecimal.valueOf(1))
        );


        // Print the order details
        System.out.println("Order ID: " + order.getId());
        System.out.println("Created At: " + order.getCreatedAt());
        System.out.println("Items:");
        for (OrderItem item : order.getItens()) {
            System.out.println("Name-> " + item.product().name() + " - Quantity-> " + item.quantity());

        }
    }
}
