package lab05.Models;

public enum PaymentMethod {
    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    CASH("Cash"),
    PAYPAL("PayPal"),
    BANK_TRANSFER("Bank Transfer");

    private final String displayName;

    /**
     * Constructor for PaymentMethod enum with a display name.
     *
     * @param displayName the name to display for the payment method
     */
    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the display name of the payment method.
     *
     * @return the display name of the payment method
     */
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
