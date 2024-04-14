//Rail Fence Cipher using Row Column Technique

#include<stdio.h>
#include<ctype.h>
#include<string.h>

int main(){
	int size=50;
	char ptext[size], ctext[size+9], key[9];
	
	printf("Enter the key :");
	fgets(key, sizeof(key), stdin);
	printf("Enter the plain text :");
	fgets(ptext, sizeof(ptext), stdin);
	
	int ptext_len = strlen(ptext);
	int key_len = strlen(key);
	
	key[--key_len] = '\0';
	ptext[--ptext_len] = '\0';
	
	int n=ptext_len%key_len;
	n = n>0?key_len-n:0;
	
	for(int i=0; i<n; i++){
		int val = i + 'x';
		if(val>'z'){
			val = val%'z' + 'a'-1;
		}
		ptext[ptext_len+i] = (char)val;
	}
	ptext_len+=n;
	ptext[ptext_len]='\0';
	
	int key_order[key_len];
	for(int i=0; i<key_len; i++){
		
	}
	
	
	/*			
	for(int i=0; i<key_len; i++){
		printf("%c\t",key[i]);
	}

	printf("\n");
	for(int i=0; i<ptext_len; i++){
		printf("%c\t",ptext[i]);
		if((i+1)%key_len==0){
			printf("\n");
		}
	}
	*/
	
}
