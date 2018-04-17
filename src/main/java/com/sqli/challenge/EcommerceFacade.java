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
  }

  public void addCapsule(String name, int quantity, int unitPrice)
  {
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
  }

  public String summary()
  {
    return null;
  }
}
