package lab03.services;

import lab03.Agreement;
import lab03.PaymentMethod;

import java.util.*;

/**
 * The {@code PaymentService} class handles payment processing for agreements,
 * including payment processing, refunds, and listing payments by agreement.
 */
public class PaymentService {
    private final Map<UUID, List<PaymentRecord>> paymentRecords = new HashMap<>();

    /**
     * Processes a payment for a given agreement.
     *
     * @param agreement the agreement for which the payment is processed
     * @param paymentMethod the payment method used for the payment
     */
    public void processPayment(Agreement agreement, PaymentMethod paymentMethod) {
        // Create a PaymentRecord for the processed payment
        PaymentRecord record = new PaymentRecord(agreement.getId(), paymentMethod, agreement.getTotalPrice());
        paymentRecords.computeIfAbsent(agreement.getId(), k -> new ArrayList<>()).add(record);

        // Log payment processing
        System.out.println("Processed payment of " + agreement.getTotalPrice() + " using " + paymentMethod + " for agreement ID: " + agreement.getId());
    }

    /**
     * Refunds a payment for a given agreement.
     *
     * @param agreementId the ID of the agreement for which the payment is refunded
     * @return true if the refund was successful, false otherwise
     */
    public boolean refundPayment(UUID agreementId) {
        List<PaymentRecord> records = paymentRecords.get(agreementId);

        if (records != null && !records.isEmpty()) {
            // Remove the last payment record as a simple refund process
            records.remove(records.size() - 1);
            System.out.println("Refunded payment for agreement ID: " + agreementId);
            return true; // Indicating that the refund was successful
        }
        return false; // No payments found for the given agreement ID
    }

    /**
     * Lists all payments associated with a given agreement.
     *
     * @param agreementId the ID of the agreement
     * @return a list of payment records for the specified agreement
     */
    public List<PaymentRecord> listPaymentsByAgreement(UUID agreementId) {
        return paymentRecords.getOrDefault(agreementId, new ArrayList<>());
    }

    /**
     * Inner class representing a payment record.
     */
    private static class PaymentRecord {
        private final UUID agreementId;
        private final PaymentMethod paymentMethod;
        private final double amount;

        public PaymentRecord(UUID agreementId, PaymentMethod paymentMethod, double amount) {
            this.agreementId = agreementId;
            this.paymentMethod = paymentMethod;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "PaymentRecord{" +
                    "agreementId=" + agreementId +
                    ", paymentMethod=" + paymentMethod +
                    ", amount=" + amount +
                    '}';
        }
    }
}
