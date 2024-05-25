/*
Write a Java program to perform encryption and decryption using the following
algorithms:
b. Play fair cipher 
*/

//Play Fair Cipher

import java.io.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class p2b{
	private static char[][] table = new char[5][5];
	
	public static void fillTable(String key){
		Map<Character, Boolean> charMap = new HashMap<>();
		for(char c='a'; c<='z';c++){
			charMap.put(c, false);
		}
		
		int i=0, j=0, k=0;
		while(k<key.length()){
			char chr = key.charAt(k);
			if(!charMap.get(chr)){
				table[i][j]=chr;
				j = ++j%5;
				i += (j==0)?1:0;
				
				if(chr == 'i' || chr == 'j'){
					charMap.put('i', true);
					charMap.put('j', true);
				}
				
				charMap.put(chr,true);
			}
			k++;
		}
		charMap.put('j',true);
		for(char c='a'; c<='z';c++){
			if(!charMap.get(c)){
				table[i][j]=c;
				j = ++j%5;
				i += (j==0)?1:0;
			}
		}
	}	
	
	public static int[] getPosition(char c){
		int[] position = new int[2];
		
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(table[i][j] == c){
					position[0] = i;
					position[1] = j;
					return position;
				}
			}
		}
		return position;
	}
	
	public static String toCipher(String text){
		int[] c1 = getPosition(text.charAt(0));
		int[] c2 = getPosition(text.charAt(1));
		int x1, y1, x2, y2;
		
		x1 = c1[0];
		y1 = c1[1];
		x2 = c2[0];
		y2 = c2[1];
		
		if(x1 == x2 && y1 == y2)
			return text;
		
		if(x1 == x2){
			y1 = (y1+1)%5;
			y2 = (y2+1)%5; 
		}else if(y1 == y2){
			x1 = (x1+1)%5;
			x2 = (x2+1)%5; 
		}else{
			y1 = c2[1];
			y2 = c1[1];
		}
		return Character.toString(table[x1][y1]) + Character.toString(table[x2][y2]);
	}
	
	public static String toDecipher(String text){
		int[] c1 = getPosition(text.charAt(0));
		int[] c2 = getPosition(text.charAt(1));
		int x1, y1, x2, y2;
		
		x1 = c1[0];
		y1 = c1[1];
		x2 = c2[0];
		y2 = c2[1];
		
		if(x1 == x2 && y1 == y2)
			return text;
		
		if(x1 == x2){
			y1 = (y1-1+5)%5;
			y2 = (y2-1+5)%5; 
		}else if(y1 == y2){
			x1 = (x1-1+5)%5;
			x2 = (x2-1+5)%5; 
		}else{
			y1 = c2[1];
			y2 = c1[1];
		}
		return Character.toString(table[x1][y1]) + Character.toString(table[x2][y2]);
	}
	
	public static String change_end_char(String text){
	if(text.charAt(0) == 'y'){
			text = text.charAt(1)+"x";
		}else if(text.charAt(0) == 'x'){
			text = text.charAt(1)+"y";
		}
		return text;
	}
	
	public static String decrypt(String text){
		String ptext = "";
		String substr;
		int text_len = text.length();
		
		for(int i=0; i<text_len; i+=2){
			substr = text.substring(i, i+2);
			ptext+= toDecipher(substr);
		}
		return ptext;
	}
	
	public static String encrypt(String text){
		String ctext = "";
		String substr;
		int text_len = text.length();
		boolean isPadded = false;
		
		if(text_len%2 != 0){
			text += 'x';
			isPadded = true;
		}
		
		for(int i=0; i<text_len; i+=2){
			substr = text.substring(i, i+2);
			if(substr.charAt(0) == substr.charAt(1)){
				substr = change_end_char(substr);
				i--;
				text_len++;
			}
			ctext+= toCipher(substr);
		}
		
		if(text_len%2 != 0 && !isPadded){
			substr = text.substring(text_len-2, text_len);
			substr = change_end_char(substr);
			ctext+= toCipher(substr);
		}
		
		return ctext;
	}
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the key: ");
		String key = input.nextLine();
		key = key.toLowerCase();
		fillTable(key);
		
		System.out.print("\nTable:\n");
		
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				System.out.print(table[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.print("\n1.Encrypt\n2.Decrypt\nOption(1/2): ");
		int opt = input.nextInt();
		
		input.nextLine();
		System.out.print("Enter the plaintext: ");
		String text = input.nextLine();
		
		if(opt==1){
			System.out.print("\nEncrypted Text:\n"+encrypt(text)+"\n");	
		}else if(opt==2){
			System.out.print("\nDecrypted Text:\n"+decrypt(text)+"\n");
		}else{
			System.out.print("Invalid!");
			return;
		}
	}
}
