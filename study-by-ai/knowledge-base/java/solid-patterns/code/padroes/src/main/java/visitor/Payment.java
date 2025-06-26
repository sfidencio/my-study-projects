package visitor;

import java.math.BigDecimal;

abstract class Payment {
    private BigDecimal amount;

    public Payment(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public abstract void accept(PaymentVisitor visitor);
}
