package com.sqli.challenge;

import java.util.Map;

final class EmptyCartRestriction implements Restriction
{
  private final Map<?, ?> machinesCartsEntries;
  private final Map<?, ?> capsulesCartsEntries;

  EmptyCartRestriction(Map<?, ?> machinesCartsEntries, Map<?, ?> capsulesCartsEntries)
  {
    this.machinesCartsEntries = machinesCartsEntries;
    this.capsulesCartsEntries = capsulesCartsEntries;
  }

  @Override
  public EvaluationResult evaluate()
  {
    return machinesCartsEntries.isEmpty() && capsulesCartsEntries.isEmpty() ? new EvaluationFailure("Empty Cart") : EvaluationResult.GREEN;
  }

}
