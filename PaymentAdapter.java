// Target Interface
interface PaymentProcessor {
    void processPayment(String customerId, double amountInDollars);
}

// Adaptee (unmodifiable)
class OldPayGateway {
    public void chargeInCents(int cents, String accountRef) {
        System.out.println("OldPayGateway: Successfully charged " + cents + " cents to account reference '" + accountRef + "'.");
    }
}

// Adapter (Object Adapter using composition)
public class PaymentAdapter implements PaymentProcessor {
    private final OldPayGateway oldPayGateway;

    public PaymentAdapter(OldPayGateway oldPayGateway) {
        this.oldPayGateway = oldPayGateway;
    }

    @Override
    public void processPayment(String customerId, double amountInDollars) {
        // Convert dollars to cents accurately
        int cents = (int) Math.round(amountInDollars * 100.0);
        oldPayGateway.chargeInCents(cents, customerId);
    }

    public static void main(String[] args) {
        String studentId = "220103167";
        System.out.println("Student ID: " + studentId);

        OldPayGateway oldGateway = new OldPayGateway();
        PaymentProcessor adapter = new PaymentAdapter(oldGateway);

        adapter.processPayment(studentId, 142.75);
    }
}
