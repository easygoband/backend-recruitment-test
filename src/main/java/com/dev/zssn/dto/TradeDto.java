package com.dev.zssn.dto;

public class TradeDto {

  private TraderDto sender;
  private TraderDto receiver;

  public TraderDto getSender() {
    return sender;
  }

  public void setSender(TraderDto sender) {
    this.sender = sender;
  }

  public TraderDto getReceiver() {
    return receiver;
  }

  public void setReceiver(TraderDto receiver) {
    this.receiver = receiver;
  }

  @Override
  public String toString() {
    return "TradeDto [receiver=" + receiver + ", sender=" + sender + "]";
  }

}
