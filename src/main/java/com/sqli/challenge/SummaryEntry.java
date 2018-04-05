package com.sqli.challenge;

class SummaryEntry {

    private int quantity;
    private int total;

    void add(int quantity, int total) {
        this.quantity += quantity;
        this.total += total;
    }

    String print() {
        return "\tQuantity: " + quantity + "\tPrice: " + total + "\n";
    }
}
