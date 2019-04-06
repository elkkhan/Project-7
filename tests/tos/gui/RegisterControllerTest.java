package tos.gui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import tos.gui.controller.RegisterController;

public class RegisterControllerTest {

  RegisterController rc = new RegisterController();

  @Test
  public void CheckRegister() {
    assertTrue(rc.isCorrectForRegister("Taha", "Nurdag", "1234", "tfn@gmail.com", "14asd213"));
    assertFalse(rc.isCorrectForRegister("123", "123sad", "", "", "11"));
    assertFalse(rc.isCorrectForRegister("", "123sad", "123", "xxxxxxxxxx", "111111111"));
    assertFalse(rc.isCorrectForRegister("", "", "123", "xxxxxxxxxx", "111111111"));
    assertFalse(rc.isCorrectForRegister("asdasd", "", "123", "xxxxxxxxxx", "111111111"));
    assertFalse(rc.isCorrectForRegister("212", "", "123", "xxxxxxxxxx", "111111111"));
  }
}
