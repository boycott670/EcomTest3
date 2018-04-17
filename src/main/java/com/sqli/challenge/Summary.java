package com.sqli.challenge;

import java.util.Map;

final class Summary
{
  private final SummaryEntry capsulesSummaryEntry = new SummaryEntry();
  private final SummaryEntry machinesSummaryEntry = new SummaryEntry();
  private int total;
  
  void addTo(final Map<? extends String, ? extends CartEntry> machinesCartEntries, final Map<? extends String, ? extends CartEntry> capsulesCartEntries)
  {
    total = 0;
    add(machinesCartEntries, machinesSummaryEntry);
    add(capsulesCartEntries, capsulesSummaryEntry);
  }
  
  private void add(final Map<? extends String, ? extends CartEntry> cartEntries, final SummaryEntry summaryEntry)
  {
    cartEntries.values()
      .forEach(cartEntry ->
      {
        cartEntry.addTo(summaryEntry);
        total += cartEntry.total();
      });
  }
  
  String print()
  {
    return new StringBuilder()
        .append("Capsules\n")
        .append(capsulesSummaryEntry.print())
        .append("\n")
        .append("Machines\n")
        .append(machinesSummaryEntry.print())
        .append("\nTotal Price: ")
        .append(total)
        .append("\n")
        .toString();
  }
}
