package state.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Order {
    private UUID id;
    private LocalDateTime createdAt;
    private List<OrderItem> itens;

    public Order() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.itens = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (this.itens.contains(item)) {
            addQuantity(item);
        } else {
            this.itens.add(item);
        }
    }

    private void addQuantity(OrderItem item) {
        for (OrderItem orderItem : this.itens) {
            if (orderItem.product().id() == item.product().id()) {
                orderItem.addQuantity(item.quantity());
                return;
            }
        }
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<OrderItem> getItens() {
        return Collections.unmodifiableList(this.itens);
    }
}
