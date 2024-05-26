//Rail Fence Cipher using Row Column Technique

#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<string.h>

int main(){
	int size=50;
	char ptext[size], ctext[size+9], key[9];
	
	printf("Enter the key :");
	fgets(key, sizeof(key), stdin);
	printf("Enter the plain text :");
	fgets(ptext, sizeof(ptext), stdin);
	
	int ptext_len = strlen(ptext)-1;
	int key_len = strlen(key)-1;
	
	//Padding
	int n=ptext_len%key_len;
	n = n>0?key_len-n:0;
	for(int i=0; i<n; i++){
		ptext[ptext_len+i] =('x'+i-'a')%26+'a';
	}
	ptext_len+=n;
	ptext[ptext_len]='\0';
	key[key_len] = '\0';
	
	//Finding the order of keys
	int order_of_key[key_len];
	for(int i=0; i<key_len; i++) {
        for(int j=0; j<key_len; j++) {
        	if(i==key[j]-'0'-1){
        		order_of_key[i]=j;
        		break;
        	}
    	}
    }
    
	//Using positions to generate final text
	int pos=0;
    for(int i=0; i<key_len; i++){
    	int k=order_of_key[i];
    	while(k<ptext_len){
    		ctext[pos]=ptext[k];
    		pos++;
    		k+=key_len;
    	}
    }
    
    int ctext_len = strlen(ctext);
    ctext[ctext_len] = '\0';
	printf("%s",ctext);
}
