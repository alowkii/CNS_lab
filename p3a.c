//Vigenere Cipher
#include<stdio.h>
#include<ctype.h>
#include<string.h>

int main(){
	int size=50,i=0;
	char ptext[size], key[size], ctext[size];

	printf("Enter the plain text: ");
	fgets(ptext, size, stdin);
	
	printf("Enter the key: ");
	fgets(key, size, stdin);
	
	while(ptext[i] != '\0' && key[i] != '\0' && i<size){
		if(isalpha(ptext[i]) && isalpha(key[i]))
			ctext[i] = ((tolower(ptext[i])-'a') + (tolower(key[i])-'a'))%26 + 'a';
		else
			ctext[i] = ptext[i];
		i++;
	}
	
	ctext[i] = '\0';
	
	printf("\nCipher text: %s",ctext);
}
