package tos.gui.model;

import java.util.Date;

public class Users extends Person {

   public Users(String name, String surname, String id, Date age, String email, byte[] salt) {
        super(name, surname, id, age, email, salt);
    }
}

