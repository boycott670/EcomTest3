package com.sqli.challenge;

import java.util.Map;

final class PackagingRuleCapsulesRestriction implements Restriction
{
  private final int multiple;
  private final Map<? extends String, ? extends CartEntry> capsulesCartsEntries;
  
  PackagingRuleCapsulesRestriction(int multiple, Map<? extends String, ? extends CartEntry> capsulesCartsEntries)
  {
    this.multiple = multiple;
    this.capsulesCartsEntries = capsulesCartsEntries;
  }

  @Override
  public EvaluationResult evaluate()
  {
    return capsulesCartsEntries
      .entrySet()
      .stream()
      .filter(capsulesCartsEntry -> !capsulesCartsEntry.getValue().isMultipleOf(multiple))
      .findFirst()
      .map(capsulesCartsEntry -> (EvaluationResult)new EvaluationFailure(String.format("%s: Invalid Quantity, must be a multiple of %d", capsulesCartsEntry.getKey(), multiple)))
      .orElse(EvaluationResult.GREEN);
  }
}
