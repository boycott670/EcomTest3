package com.sqli.challenge;

import java.util.Optional;

import static java.util.Optional.empty;

class CartEntry {
    private final int quantity;
    private final int unitPrice;

    CartEntry(int quantity, int unitPrice) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    Optional<CartEntry> groupWith(CartEntry other) {
        final int effectiveQuantity = this.quantity + other.quantity;
        if (quantity > 0) {
            return Optional.of(new CartEntry(effectiveQuantity, this.unitPrice));
        }
        return  empty();
    }

    String print() {
        return "Quantity: " + quantity + "\tPrice: " + total();
    }

    int total() {
        return quantity * unitPrice;
    }

    void addTo(SummaryEntry summaryEntry) {
        summaryEntry.add(quantity, total());
    }

    boolean hasQuantityMultipleOf(int multiple) {
        return quantity % multiple == 0;
    }
}
