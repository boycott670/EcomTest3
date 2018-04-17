package com.sqli.challenge;

interface EvaluationResult
{
  EvaluationResult GREEN = new EvaluationResult()
  {

    @Override
    public boolean hasErrors()
    {
      return false;
    }

    @Override
    public String errors()
    {
      return null;
    }
  };

  boolean hasErrors();

  String errors();
}
