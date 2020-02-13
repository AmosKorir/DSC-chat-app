package com.aurgment.dcchat;

/**
 * Created by Korir on 2/13/20.
 * amoskrr@gmail.com
 */
public class MessageModel {
  private String messageId;
  private String message;

  public MessageModel() {
  }

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
