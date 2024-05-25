//Play Fair Cipher
import java.io.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class p2b{
	private static char[][] table= new char[5][5];
		
	public static void fillTable(String key){
		Map<Character, Boolean> charMap = new HashMap<>();
		for (char c = 'a'; c <= 'z'; c++) {
            charMap.put(c, false);
        }
        
		int k=0, i=0, j=0, table_char_count=0;
		
		while(k<key.length()){
			char chr = key.charAt(k);
			if(!charMap.get(chr)){
				table[i][j] = chr;
				j++;
				table_char_count++;
				if(j==5){
					j=0;
					i++;
				}
				if(chr == 'i' || chr == 'j'){
					charMap.put('i', true);
					charMap.put('j', true);
				}
				charMap.put(chr, true);
			}
			k++;
		}
		
		for (char c = 'a'; c <= 'z'; c++) {
            if(c != 'j' && !charMap.get(c)){
            	table[(int)(table_char_count/5)][table_char_count%5] = c;
            	table_char_count++;
            	charMap.put(c, true);
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
		
		return null;
	}
	
	public static String toCipher(String text){
		int[] c1 = getPosition(text.charAt(0));
		int[] c2 = getPosition(text.charAt(1));
		int x1, y1, x2, y2;
		
		x1 = c1[0];
		y1 = c1[1];
		x2 = c2[0];
		y2 = c2[1];
		
		System.out.print(Character.toString(table[x1][y1]) + Character.toString(table[x2][y2]));
		
		if(x1 == x2 && y1 == y2)
			return text;
			
		if(x1 == x2){
			y1=(y1+1)%5;
			y2=(y2+1)%5;
			return Character.toString(table[x1][y1]) + Character.toString(table[x2][y2]);
		}
		
		if(y1 == y2){
			x1=(x1+1)%5;
			x2=(x2+1)%5;
			return Character.toString(table[x1][y1]) + Character.toString(table[x2][y2]);
		}
		

		return Character.toString(table[x1][y2]) + Character.toString(table[x2][y1]);
	}
	
	public static String encrypt(String text){
		if(text.length()%2 !=0){
			text+='x';
		}
		String ctext = "";
		String substr;
		for(int i=0; i<text.length(); i+=2){
			substr = text.substring(i,i+2);
			if(substr.charAt(0)==substr.charAt(1) && substr.charAt(0)!='x'){
				substr=substr.charAt(0)+"x";
				i--;
			}
			if(substr.charAt(0)==substr.charAt(1) && substr.charAt(0)=='x'){
				substr=substr.charAt(0)+"y";
				i--;
			}
			ctext+= toCipher(substr);
		}
		return ctext;
	}	
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the key:");
		String key = input.nextLine();
		key = key.toLowerCase();
		fillTable(key);
		
		input.nextLine();
		System.out.print("Enter the plaintext:");
		String ptext = input.nextLine();
		System.out.print("\nEncrypted Text:\n"+encrypt(ptext)+"\n");
		
		System.out.print("\nTable:\n");
		
		for(int i=0; i<5; i++){
			for(int j=0;j<5;j++){
				System.out.print(table[i][j]+" ");
			}
			System.out.println ();
		}
		
	}
}
