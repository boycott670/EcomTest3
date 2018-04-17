package com.sqli.challenge;

public class EcommerceFacade
{
  private final Cart cart = new Cart();
  
  public void addMachine(String name, int quantity, int unitPrice)
  {
    cart.addMachine(name, new CartEntry(quantity, unitPrice));
  }

  public void removeMachine(String name, int quantity)
  {
    addMachine(name, -quantity, 0);
  }

  public void addCapsule(String name, int quantity, int unitPrice)
  {
    cart.addCapsule(name, new CartEntry(quantity, unitPrice));
  }

  public String cartContent()
  {
    return cart.content();
  }

  public EcommerceFacade order()
  {
    return this;
  }

  public boolean hasErrors()
  {
    return false;
  }

  public String errors()
  {
    return null;
  }

  public void voucher(String code)
  {
  }

  public void removeCapsule(String name, int quantity)
  {
    addCapsule(name, -quantity, 0);
  }

  public String summary()
  {
    final Summary summary = new Summary();
    cart.addTo(summary);
    return summary.print();
  }
}
