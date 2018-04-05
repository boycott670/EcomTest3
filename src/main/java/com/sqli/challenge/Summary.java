package com.sqli.challenge;

import java.util.Map;

class Summary {

    private final SummaryEntry capsulesEntry;
    private final SummaryEntry machinesEntry;
    private int total;

    Summary() {
        capsulesEntry = new SummaryEntry();
        machinesEntry = new SummaryEntry();
    }

    void add(Map<String, CartEntry> capsuleCartEntries, Map<String, CartEntry> machineCartEntries) {
        add(capsuleCartEntries, capsulesEntry);
        add(machineCartEntries, machinesEntry);
    }

    private void add(Map<String, CartEntry> capsulesCartEntries, SummaryEntry summaryEntry) {
        capsulesCartEntries.values().forEach(cartEntry -> {
            cartEntry.addTo(summaryEntry);
            total += cartEntry.total();
        });
    }

    String print() {
        StringBuilder sb = new StringBuilder();
        return sb.append("Capsules\n")
                .append(capsulesEntry.print())
                .append("Machines\n")
                .append(machinesEntry.print())
                .append("Total Price: ")
                .append(total)
                .append("\n")
                .toString();
    }
}
