package tos.chat;

import org.junit.jupiter.api.Test;
import tos.chat.ScreenShower;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestScreenShower {

  ScreenShower ss = new ScreenShower();

  @Test
  public void isAllCharactersDigits() {
    assertTrue(ss.isAllDigits("2222"));
    assertTrue(ss.isAllDigits("2020"));
    assertTrue(ss.isAllDigits("1109"));
    assertFalse(ss.isAllDigits("1f09"));
    assertFalse(ss.isAllDigits("port"));
    assertFalse(ss.isAllDigits("port2108"));
  }


}
