package com.dev.zssn.trade;

public class Trade {

  private SurvivorTrade sender;
  private SurvivorTrade receiver;

  public Trade(SurvivorTrade sender, SurvivorTrade receiver) {
    this.sender = sender;
    this.receiver = receiver;
  }

  public SurvivorTrade getSender() {
    return sender;
  }

  public SurvivorTrade getReceiver() {
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
