package visitor;

import java.math.BigDecimal;

public class Application {
    public static void main(String[] args) {
        Payment payment = new PaymentCreditCard(new BigDecimal(200.209));
        payment.accept(new TransactionPrerequisteVisitor());
        System.out.println("---------------------");

        Payment payment2 = new PaymentPix(new BigDecimal(145.788));
        payment2.accept(new TransactionPrerequisteVisitor());
        System.out.println("---------------------");
    }
}
