package tos.chat;

import org.junit.jupiter.api.Test;
import tos.chat.Client.Client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestClient {
    Client c = new Client();
    ScreenShower ss = new ScreenShower();
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
@Test
public  void isAllCharactersDigits(){
    assertTrue(ss.isAllDigits("2222"));
    assertTrue(ss.isAllDigits("2020"));
    assertTrue(ss.isAllDigits("1109"));
    assertFalse(ss.isAllDigits("1f09"));
    assertFalse(ss.isAllDigits("port"));
    assertFalse(ss.isAllDigits("port2108"));
}
}
