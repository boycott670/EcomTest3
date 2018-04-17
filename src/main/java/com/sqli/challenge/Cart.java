package com.sqli.challenge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

final class Cart
{
  private static final int CAPSULES_QUANTITY_FACTOR = 5;
  
  private final Map<String, CartEntry> machinesCartEntries = new HashMap<>();
  private final Map<String, CartEntry> capsulesCartEntries = new HashMap<>();
  
  private String voucher;
  
  void addMachine(final String name, final CartEntry cartEntry)
  {
    addProduct(name, cartEntry, machinesCartEntries);
  }
  
  void addCapsule(final String name, final CartEntry cartEntry)
  {
    addProduct(name, cartEntry, capsulesCartEntries);
  }
  
  private void addProduct(final String name, final CartEntry cartEntry, final Map<? super String, CartEntry> productscartEntries)
  {
    if (productscartEntries.get(name) != null)
    {
      updateProduct(name, cartEntry, productscartEntries);
    }
    else
    {
      productscartEntries.put(name, cartEntry);
    }
  }
  
  private void updateProduct(final String name, final CartEntry cartEntry, final Map<? super String, CartEntry> productscartEntries)
  {
    final Optional<CartEntry> updatedCartEntry = productscartEntries.get(name).groupWith(cartEntry);
    
    if (updatedCartEntry.isPresent())
    {
      productscartEntries.put(name, updatedCartEntry.get());
    }
    else
    {
      productscartEntries.remove(name);
    }
  }
  
  private String cartEntriesContent(final String header, final Map<? extends String, ? extends CartEntry> cartEntries)
  {
    if (cartEntries.isEmpty())
    {
      return "";
    }
    
    final StringBuilder builder = new StringBuilder();
    
    builder.append(header + "\n");
    
    final Map<? extends String, ? extends CartEntry> sortedCartEntries = new TreeMap<>(cartEntries);
    
    sortedCartEntries.forEach((name, cartEntry) -> builder.append(String.format("\tName: %s%s\n", name, cartEntry.content())));
    
    return builder.toString();
  }
  
  String content()
  {
    return cartEntriesContent("Capsules", capsulesCartEntries) + cartEntriesContent("Machines", machinesCartEntries);
  }
  
  void addTo(final Summary summary)
  {
    summary.addTo(machinesCartEntries, capsulesCartEntries);
  }
  
  CartRestrictions createRestrictions()
  {
    final Collection<Restriction> restrictions = new ArrayList<>();
    
    restrictions.add(new EmptyCartRestriction(machinesCartEntries, capsulesCartEntries));
    restrictions.add(new PackagingRuleCapsulesRestriction(CAPSULES_QUANTITY_FACTOR, capsulesCartEntries));
    
    if (voucher != null && !voucher.trim().isEmpty())
    {
      restrictions.add(new VoucherCodeRestriction(voucher, machinesCartEntries));
    }
    
    return new CartRestrictions(restrictions);
  }
  
  void addVoucher(final String voucher)
  {
    this.voucher = voucher;
  }
}
