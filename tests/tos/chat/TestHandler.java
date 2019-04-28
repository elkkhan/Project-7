package tos.chat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import tos.chat.Server.*;

public class TestHandler {

  Server server = new Server();
  MessageType messageTypecorrect = MessageType.TEXT;
  MessageType messageTypeincorrect = MessageType.USER_ADDED;
  MessageType messageTypeincorrect2 = MessageType.NAME_REQUEST;

  @Test
  public void isInputText() {
    assertTrue(server.isTextType(messageTypecorrect));
    assertFalse(server.isTextType(messageTypeincorrect));
    assertFalse(server.isTextType(messageTypeincorrect2));
  }
}
