package com.sqli.challenge;

import java.util.Map;

final class VoucherCodeRestriction implements Restriction
{
  private final String voucherCode;
  private final Map<?, ?> machinesCartsEntries;

  VoucherCodeRestriction(String voucherCode, Map<?, ?> machinesCartsEntries)
  {
    this.voucherCode = voucherCode;
    this.machinesCartsEntries = machinesCartsEntries;
  }

  @Override
  public EvaluationResult evaluate()
  {
    return !voucherCode.replaceAll("\\d+", "").isEmpty() ? new EvaluationFailure("Invalid voucher code") : (machinesCartsEntries.isEmpty() ? new EvaluationFailure("Voucher requires machine purchase") : EvaluationResult.GREEN);
  }

}
