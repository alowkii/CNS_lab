//Write a Java program to implement RSA algorithm logic.
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA{
	private BigInteger n, d, e;
	private int bitlen = 1024;
	
	public RSA(int bits){
		bitlen = bits;
		generateKeys();
	}
	
	public synchronized void generateKeys() {
		SecureRandom r = new SecureRandom();
		BigInteger p = new BigInteger(bitlen/2, 100, r);
		BigInteger q = new BigInteger(bitlen/2, 100, r);
		n = p.multiply(q);
		BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		e = new BigInteger("3");
		while(phi.gcd(e).intValue() > 1) {
			e = e.add(new BigInteger("2"));
		}
		d = e.modInverse(phi);
	}
	
	public synchronized String encrypt(String message){
		return (new BigInteger(message.getBytes())).modPow(e, n).toString();
	}
	
	public synchronized String decrypt(String message){
		return new String((new BigInteger(message)).modPow(d, n).toByteArray());
	}
	
	public static void main(String[] args){
		RSA rsa = new RSA(1024);
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the plaintext: ");
		String plaintext = input.nextLine();
		
		String ciphertext = rsa.encrypt(plaintext);
		System.out.println("Encrypted: "+ciphertext);
		
		String decryptedtext = rsa.decrypt(ciphertext);
		System.out.println("Decrypted: "+decryptedtext);
		
		input.close();
	}
}

