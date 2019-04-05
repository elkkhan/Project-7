package tos.chat;

import org.junit.jupiter.api.Test;
import tos.chat.Client.Client;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestClient {
    Client c = new Client();

@Test
 public void FirstMustCapital(){
    assertTrue(c.isFirstCapital("D"));
    assertFalse(c.isFirstCapital("d"));
}
@Test
public void isCorrectIPinput(){
    assertTrue(c.isValidIP("192.168.0.3"));
    assertTrue(c.isValidIP("192.168.10.38"));
    assertTrue(c.isValidIP("localhost"));
    assertFalse(c.isValidIP("lcolhost"));
    assertFalse(c.isValidIP("1222.12134.54.4"));
    assertFalse(c.isValidIP("122.2134.54.4"));
}
}
