package com.sqli.challenge;

final class EvaluationFailure implements EvaluationResult
{
  private final String errorMessage;

  EvaluationFailure(String errorMessage)
  {
    this.errorMessage = errorMessage;
  }

  @Override
  public boolean hasErrors()
  {
    return true;
  }

  @Override
  public String errors()
  {
    return errorMessage;
  }
}
