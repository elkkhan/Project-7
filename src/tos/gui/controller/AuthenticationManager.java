package tos.gui.controller;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import tos.common.*;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import tos.gui.model.PasswordPair;
import tos.gui.model.Users;


public class AuthenticationManager {
	private static final SecureRandom RAND = new SecureRandom();
	public boolean authenticateUser(String email, char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {


		return false;
	}
	

	public boolean authenticateAdmin(String email, char[] password)
	{

		return false;
	}
	
	private void showMessage(String title, String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(contentText);
		alert.showAndWait();
	}


	public Users registerUser(String name, String surname, String id, Date age, String email, char[] password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		PasswordPair p = hashPassword(password);
		return new Users(name, surname, id, age, email, p.getSalt());
	}



	public byte[] hashPassword(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		PBEKeySpec spec = new PBEKeySpec(password, salt, 2000, 256);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		return skf.generateSecret(spec).getEncoded();
	}

	/* We will use this function when we will have a database dont delete this. */
/*	private boolean constantTimeEquals(byte[] a, byte[] b) {
		if (a.length != b.length) {
			return false;
		}

		int result = 0;
		for (int i = 0; i < a.length; i++) {
			result |= a[i] ^ b[i];
		}

		return result == 0;
	}
	*/

	private PasswordPair hashPassword(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] salt = new byte[256];
		RAND.nextBytes(salt);
		return new PasswordPair(hashPassword(password, salt), salt);
	}





}
