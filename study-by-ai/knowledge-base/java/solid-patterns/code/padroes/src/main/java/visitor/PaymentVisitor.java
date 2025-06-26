package visitor;

public interface PaymentVisitor {
    void visitCheckFraud(Payment payment);

    void visitCheckBalance(Payment payment);
}