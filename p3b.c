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
	
	int order_of_key[key_len];
	for(int i=0; i<key_len; i++) {
        for(int j=0; j<key_len; j++) {
        	if((char)(i+1+'0')==key[j]){
        		order_of_key[i]=j;
        		break;
        	}
    	}
    }
	
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
    ctext[ctext_len-2] = '\0';
	printf("%s",ctext);
}
