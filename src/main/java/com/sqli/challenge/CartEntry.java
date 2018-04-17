package com.sqli.challenge;

import java.util.Optional;

final class CartEntry
{
  private final int quantity;
  private final int unitPrice;
  
  CartEntry(int quantity, int unitPrice)
  {
    this.quantity = quantity;
    this.unitPrice = unitPrice;
  }
  
  Optional<CartEntry> groupWith(final CartEntry otherCartEntry)
  {
    final int groupedQuantity = quantity + otherCartEntry.quantity;
    
    if (groupedQuantity <= 0)
    {
      return Optional.empty();
    }
    
    return Optional.of(new CartEntry(groupedQuantity, unitPrice));
  }
  
  private int total()
  {
    return quantity * unitPrice;
  }
  
  String content()
  {
    return String.format("\tQuantity: %d\tPrice: %d", quantity, total());
  }
}
