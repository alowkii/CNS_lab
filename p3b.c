//Rail Fence Cipher using Row Column Technique

#include<stdio.h>
#include<string.h>
#define size 50

int main(){
	char ptext[size], ctext[size+9], key[9];
	
	printf("Enter the key: ");
	scanf("%s", key);
	printf("Enter the text: ");
	scanf("%s", ptext);
	
	int ptext_len = strlen(ptext);
	int key_len = strlen(key);
	
	//Padding
	int n = ptext_len%key_len;
	n = n>0? key_len-n: 0;
	for(int i=0; i<n; i++){
		ptext[ptext_len++] = ('x'+i-'a')%26+'a';
	}
	ptext[ptext_len] = '\0';
	
	//Order finding
	int order_of_key[key_len];
	for(int i=0; i<key_len; i++){
		for(int j=0; j<key_len; j++){
			if(i==key[j]-'0'-1){
				order_of_key[i] = j;
				break;
			}
		}
	}
	
	//Cipher text creation
	int pos=0;
	for(int i=0; i<key_len; i++){
		int chr_pos = order_of_key[i];
		while(chr_pos<ptext_len){
			ctext[pos] = ptext[chr_pos];
			chr_pos+=key_len;
			pos++;
		}
	}
	
	ctext[ptext_len] = '\0';
	printf("Cipher Text: %s",ctext);
}
