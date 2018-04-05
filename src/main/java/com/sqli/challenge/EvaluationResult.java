package com.sqli.challenge;

abstract class EvaluationResult {

    static final EvaluationResult GREEN = new EvaluationResult() {
        @Override
        boolean hasError() {
            return false;
        }

        @Override
        String getError() {
            return null;
        }
    };

    abstract boolean hasError();

    abstract String getError();
}
