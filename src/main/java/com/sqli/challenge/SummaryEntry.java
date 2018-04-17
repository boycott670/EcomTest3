package com.sqli.challenge;

final class SummaryEntry
{
  private int quantity;
  private int total;
  
  void add(final int quantity, final int total)
  {
    this.quantity += quantity;
    this.total += total;
  }
  
  String print()
  {
    return new StringBuilder()
      .append("\tQuantity: ")
      .append(quantity)
      .append("\tPrice: ")
      .append(total)
      .toString();
  }
}
