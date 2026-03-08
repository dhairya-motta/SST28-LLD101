package com.example.payments;

import java.util.Objects;

/**
 * SafeCashAdapter - Adapter that adapts SafeCashClient to PaymentGateway interface.
 */
public class SafeCashAdapter implements PaymentGateway {

    private final SafeCashClient client;

    public SafeCashAdapter(SafeCashClient client) {
        this.client = Objects.requireNonNull(client, "client");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        // Adapt the interface: SafeCashClient.createPayment(amount, user).confirm()
        // SafeCashClient uses (amount, user) where user appears to be customerId
        // Returns transaction ID in format "SC#pay(user,amount)"
        SafeCashPayment payment = client.createPayment(amountCents, customerId);
        return payment.confirm();
    }
}

