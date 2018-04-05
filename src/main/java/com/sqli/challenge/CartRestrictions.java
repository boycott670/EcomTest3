package com.sqli.challenge;

import java.util.List;

public class CartRestrictions implements Restriction {

    private final List<Restriction> restrictions;

    CartRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }


    @Override
    public EvaluationResult evaluate() {
        return restrictions.stream().map(Restriction::evaluate).filter(result -> result != EvaluationResult.GREEN).findFirst().orElse(EvaluationResult.GREEN);
    }
}
