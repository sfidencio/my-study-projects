package visitor;

import java.math.BigDecimal;

public class PaymentPix extends Payment {

    public PaymentPix(BigDecimal amount) {
        super(amount);
    }

    @Override
    public void accept(PaymentVisitor visitor) {
        System.out.println("PaymentPix");
        visitor.visitCheckBalance(this);
    }
}
