package com.sqli.challenge;

class EvaluationFailure extends EvaluationResult {

    private String error;

    EvaluationFailure(String error) {
        this.error = error;
    }

    @Override
    boolean hasError() {
        return true;
    }

    @Override
    String getError() {
        return error;
    }
}
