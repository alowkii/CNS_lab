//Write a Java program to implement AES algorithm.
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class p6{
	public static void main(String[] args){
		try{
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			SecretKey secretKey = keyGen.generateKey();
			String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
			System.out.println("Generated Key:"+encodedKey);
			Scanner input = new Scanner(System.in);
			System.out.print("Plain Text: ");
			String plainText = input.nextLine();
			String encryptedText = encrypt(plainText, secretKey);
			System.out.println("Encrypted Text: "+encryptedText);
			String decryptedText = decrypt(encryptedText, secretKey);
			System.out.println("Decrypted Text: "+decryptedText);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}
	
	public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		return new String(decryptedBytes);
	}
}
