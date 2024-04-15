//Caesar Cipher
import java.io.*;
import java.util.Scanner;

public class p2a {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the key:");
		int key = input.nextInt();
		
		input.nextLine();
		System.out.print("Enter the string:");
		String string = input.nextLine();
		
		System.out.print("Do you want to 1.Encrypt or 2.Decrypt?(1/2): ");
		int opt = input.nextInt();
		
		String message;
		if(opt == 1){
			message = encrypt(key,string);
		}else if(opt == 2){
			message = decrypt(key,string);
		}else{
			System.out.print("Unknown option.");
			return;
		}
		
		System.out.println("\nRequired text:"+message);
		
		
	}
	
	public static String encrypt(int key, String string){
		String message="";
		for(int i=0; i<string.length(); i++){
			message+=(char)(((int)string.charAt(i)+key)%(26+(int)'a'));
		}
		return message;
	}
	
	public static String decrypt(int key, String string){
		String message="";
		for(int i=0; i<string.length(); i++){
			message+=(char)(((int)string.charAt(i)-key+(26+(int)'a'))%(26+(int)'a'));
		}
		return message;
	}
	
}
