package com.sqli.challenge;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

class Cart {

    private static final int MULTIPLE_OF_CAPSULES = 5;
    private final Map<String, CartEntry> machineCartEntries;
    private final Map<String, CartEntry> capsulesCartEntries;
    private String voucher;

    Cart() {
        machineCartEntries = new HashMap<>();
        capsulesCartEntries = new HashMap<>();
    }

    void addMachine(String name, CartEntry cartEntry) {
        addProduct(name, cartEntry, this.machineCartEntries);
    }

    void addCapsules(String name, CartEntry cartEntry) {
        addProduct(name, cartEntry, capsulesCartEntries);
    }

    private void addProduct(String name, CartEntry cartEntry, Map<String, CartEntry> cartEntries) {
        if(cartEntries.get(name) != null) {
            updateCartEntry(name, cartEntry, cartEntries);
        } else {
            cartEntries.put(name, cartEntry);
        }
    }

    private void updateCartEntry(String name, CartEntry cartEntry, Map<String, CartEntry> cartEntries) {
        final Optional<CartEntry> effectiveCartEntry = cartEntries.get(name).groupWith(cartEntry);
        if(effectiveCartEntry.isPresent()) {
            cartEntries.put(name, effectiveCartEntry.get());
        } else {
            cartEntries.remove(name);
        }
    }

    String print() {
        return printCartEntries("Capsules", capsulesCartEntries) + printCartEntries("Machines", this.machineCartEntries);
    }

    private String printCartEntries(String label, Map<String, CartEntry> cartEntries) {
        if(cartEntries.isEmpty()) {
            return "";
        }
        Map<String, CartEntry> sortedMachineCartEntries = new TreeMap<>(String::compareTo);
        sortedMachineCartEntries.putAll(cartEntries);
        StringBuilder sb = new StringBuilder();
        sb.append(label).append("\n");
        sortedMachineCartEntries.forEach((name, cartEntry) -> sb.append("\tName: ").append(name).append("\t").append(cartEntry.print()).append("\n"));
        return  sb.toString();
    }

    void addTo(Summary summary) {
        summary.add(capsulesCartEntries, machineCartEntries);
    }

    CartRestrictions createRestrictions() {
        List<Restriction> restrictions = new ArrayList<>();
        restrictions.add(new EmptyCartRestriction(this));
        restrictions.add(new PackagingRuleRestriction(capsulesCartEntries, MULTIPLE_OF_CAPSULES));
        if (StringUtils.isNotBlank(voucher)) {
            restrictions.add(new VoucherRestriction(voucher, !machineCartEntries.isEmpty()));
        }
        return new CartRestrictions(restrictions);
    }

    boolean isEmpty() {
        return capsulesCartEntries.isEmpty() && machineCartEntries.isEmpty();
    }

    void addVoucher(String voucher) {
        this.voucher = voucher;
    }
}
