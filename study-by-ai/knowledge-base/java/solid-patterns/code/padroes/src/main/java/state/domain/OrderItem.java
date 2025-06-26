package state.domain;

import java.math.BigDecimal;
import java.util.Objects;

public record OrderItem(
        Product product,
        BigDecimal quantity
) {
    public OrderItem() {
        this(new Product(), BigDecimal.ZERO);
    }

    public void addQuantity(BigDecimal quantity) {
        if (quantity.compareTo(BigDecimal.ZERO) > 0) {
            this.quantity.add(quantity);
        } else {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(product.id(), orderItem.product.id()) && Objects.equals(quantity, orderItem.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }
}
