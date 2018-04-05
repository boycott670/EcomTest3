package com.sqli.challenge;

import java.util.Map;

public class PackagingRuleRestriction implements Restriction {

    private final Map<String, CartEntry> cartEntries;
    private final int packagingMultiple;

    PackagingRuleRestriction(Map<String, CartEntry> cartEntries, int packagingMultiple) {
        this.cartEntries = cartEntries;
        this.packagingMultiple = packagingMultiple;
    }

    @Override
    public EvaluationResult evaluate() {
        return cartEntries.entrySet()
                .stream()
                .filter(entry -> !entry.getValue().hasQuantityMultipleOf(packagingMultiple))
                .findFirst()
                .map(entry -> (EvaluationResult) new EvaluationFailure(entry.getKey() + ": Invalid Quantity, must be a multiple of " + packagingMultiple))
                .orElse(EvaluationResult.GREEN);
    }
}
