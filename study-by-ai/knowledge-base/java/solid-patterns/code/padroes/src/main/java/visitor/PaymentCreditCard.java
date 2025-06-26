package visitor;

import java.math.BigDecimal;

public class PaymentCreditCard extends Payment {

    public PaymentCreditCard(BigDecimal amount) {
        super(amount);
    }

    @Override
    public void accept(PaymentVisitor visitor) {
        System.out.println("PaymentCreditCard");
        visitor.visitCheckBalance(this);
        visitor.visitCheckFraud(this);
    }
}
