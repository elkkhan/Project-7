package tos.chat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestServer {
  Server server = new Server();

  @Test
  public void strMUSTbeInteger() {
    assertTrue(server.isInteger("1025"));
    assertTrue(server.isInteger("30002"));
    assertTrue(server.isInteger("2"));
    assertFalse(server.isInteger("ef72"));
    assertFalse(server.isInteger("port3030"));
    assertFalse(server.isInteger("3030port"));
  }

  @Test
  public void correctRangeForPortNumber() {
    assertTrue(server.isCorrectPort(1025));
    assertTrue(server.isCorrectPort(60002));
    assertTrue(server.isCorrectPort(5503));
    assertFalse(server.isCorrectPort(1023));
    assertFalse(server.isCorrectPort(1010));
    assertFalse(server.isCorrectPort(100500));
  }
}
