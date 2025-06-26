package visitor;

import java.math.BigDecimal;

public class TransactionPrerequisteVisitor implements PaymentVisitor {
    @Override
    public void visitCheckFraud(Payment payment) {
        System.out.println("Checking fraud for payment of " + payment.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Override
    public void visitCheckBalance(Payment payment) {
        System.out.println("Checking balance for payment of " + payment.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}
