import java.util.Date;
import java.util.Map;


/**
 * Class representing a Credit Card Payment.
 */
class CreditCardPayment extends Payment {

    /**
     * Constructs a CreditCardPayment with the specified details.
     *
     * @param amount         the payment amount
     * @param currency       the currency of the payment
     * @param customerInfo   a map containing customer details (e.g., name, email)
     * @param paymentDetails a map containing credit card details (e.g., card number, expiry, cvv)
     */
    public CreditCardPayment(double amount, String currency, Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        super(amount, currency, customerInfo, paymentDetails);
    }

    /**
     * Validates the credit card payment.
     * Checks for a positive amount, supported currency, presence of customer email, and a valid card number.
     *
     * @return true if the payment is valid; false otherwise
     */
    @Override
    public boolean validatePayment() {
        if (amount <= 0) {
            return false;
        }
        if (!("USD".equals(currency) || "EUR".equals(currency) || "GBP".equals(currency))) {
            return false;
        }
        if (!customerInfo.containsKey("email")) {
            return false;
        }
        // Validate card number length (minimum 12 characters)
        String cardNumber = paymentDetails.getOrDefault("card_number", "");
        return cardNumber.length() >= 12;
    }

    /**
     * Processes the credit card payment by connecting to the Credit Card API.
     *
     * @param config configuration map containing the "credit_card_endpoint"
     * @return a map with the result including a status and a generated transaction id
     */
    @Override
    public Map<String, String> processPayment(Map<String, String> config) {
        System.out.println("Connecting to Credit Card API at " + config.get("credit_card_endpoint"));
        String transactionId = "CC" + new Date().getTime();
        System.out.println("Processing credit card payment for " + customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}