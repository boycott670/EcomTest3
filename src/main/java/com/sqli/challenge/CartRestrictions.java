package com.sqli.challenge;

import java.util.Collection;

final class CartRestrictions implements Restriction
{
  private final Collection<? extends Restriction> restrictions;

  CartRestrictions(Collection<? extends Restriction> restrictions)
  {
    this.restrictions = restrictions;
  }

  @Override
  public EvaluationResult evaluate()
  {
    return restrictions
      .stream()
      .map(Restriction::evaluate)
      .filter(evaluationResult -> EvaluationResult.GREEN != evaluationResult)
      .findFirst()
      .orElse(EvaluationResult.GREEN);
  }
}
