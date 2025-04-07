import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ConfigurationManager is responsible for loading sensitive configuration details
 * (such as API endpoints and keys) from environment variables or secure files.
 */
public class ConfigurationManager {
    private Map<String, String> config = new HashMap<>();

    /**
     * Constructs a ConfigurationManager that loads configuration details.
     */
    public ConfigurationManager() {
        // Load configuration details from environment variables (or use default values)
        config.put("stripe_api_endpoint", System.getenv().getOrDefault("STRIPE_API_ENDPOINT", "https://api.stripe.com/process"));
        config.put("paypal_api_endpoint", System.getenv().getOrDefault("PAYPAL_API_ENDPOINT", "https://api.paypal.com/process"));
        config.put("stripe_api_key", System.getenv().getOrDefault("STRIPE_API_KEY", "default_stripe_key"));
        config.put("paypal_api_key", System.getenv().getOrDefault("PAYPAL_API_KEY", "default_paypal_key"));
    }

    /**
     * Returns the configuration value for the given key.
     *
     * @param key the configuration key
     * @return the configuration value associated with the key
     */
    public String getConfigValue(String key) {
        return config.get(key);
    }
}



