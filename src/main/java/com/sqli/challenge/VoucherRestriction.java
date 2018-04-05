package com.sqli.challenge;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class VoucherRestriction implements Restriction {

    private String voucher;
    private boolean cartHasMachine;

    VoucherRestriction(String voucher, boolean cartHasMachine) {
        this.voucher = voucher;
        this.cartHasMachine = cartHasMachine;
    }

    @Override
    public EvaluationResult evaluate() {
        return !isNumeric(voucher) ? new EvaluationFailure("Invalid voucher code") :
                (cartHasMachine ? EvaluationResult.GREEN : new EvaluationFailure("Voucher requires machine purchase"));
    }
}
