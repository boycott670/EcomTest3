package com.sqli.challenge;

public class EmptyCartRestriction implements Restriction {

    private Cart cart;

    EmptyCartRestriction(Cart cart) {
        this.cart = cart;
    }

    @Override
    public EvaluationResult evaluate() {
        return cart.isEmpty() ? new EvaluationFailure("Empty Cart") : EvaluationResult.GREEN;
    }
}
