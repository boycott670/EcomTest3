package com.sqli.challenge;

public class EcommerceFacade {

    private final Cart cart;
    private EvaluationResult evaluationResult;

    public EcommerceFacade() {
        cart = new Cart();
    }

    public void addMachine(String name, int quantity, int unitPrice) {
        cart.addMachine(name, new CartEntry(quantity, unitPrice));
    }

    public void removeMachine(String name, int quantity) {
        addMachine(name, -1 * quantity, 0);
    }

    public void addCapsule(String name, int quantity, int unitPrice) {
        cart.addCapsules(name, new CartEntry(quantity, unitPrice));
    }

    public String cartContent() {
        return cart.print();
    }

    public EcommerceFacade order() {
        this.evaluationResult = cart.createRestrictions().evaluate();
        return this;
    }

    public boolean hasErrors() {
        return evaluationResult.hasError();
    }

    public String errors() {
        return evaluationResult.getError();
    }

    public void voucher(String code) {
        cart.addVoucher(code);
    }

    public void removeCapsule(String name, int quantity) {
        addCapsule(name, -1 * quantity, 0);
    }

    public String summary() {
        final Summary summary = new Summary();
        cart.addTo(summary);
        return  summary.print();
    }
}
