package com.example.payments;

/**
 * FastPayAdapter - Adapter that adapts FastPayClient to PaymentGateway interface.
 */
public class FastPayAdapter implements PaymentGateway {

    private final FastPayClient client;

    public FastPayAdapter(FastPayClient client) {
        this.client = client;
    }

    @Override
    public String charge(String customerId, int amountCents) {
        // Adapt the interface: FastPayClient.payNow(customerId, amountCents)
        // returns transaction ID in format "FP#custId:amount"
        return client.payNow(customerId, amountCents);
    }
}

