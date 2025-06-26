package state.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record Product(
        int id,
        String name,
        String description,
        BigDecimal price
) {
    public Product() {
        this(0, "", "", BigDecimal.ZERO);
    }
}

