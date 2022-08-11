package com.dev.zssn.trade;

public class Trade {

  private Trader sender;
  private Trader receiver;

  public Trade(Trader sender, Trader receiver) {
    this.sender = sender;
    this.receiver = receiver;
  }

  public Trader getSender() {
    return sender;
  }

  public Trader getReceiver() {
    return receiver;
  }

  public boolean isAvailableToTrade() {
    return this.isEqualsValues() && receiver.isAvailableToTrade() && sender.isAvailableToTrade();
  }

  private boolean isEqualsValues() {
    return sender.getTradeValue() == receiver.getTradeValue();
  }

  @Override
  public String toString() {
    return "\n[\n receiver=" + receiver + "\n sender=" + sender + "\n isAvailableToTrade=" + isAvailableToTrade() + "\n]";
  }

}
