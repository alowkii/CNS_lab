#include<stdio.h>
#include<string.h>

int main(){
	char str[] = "hello world";
	
	int len = strlen(str);
	
	printf("\nAND:\n");
	for(int i=0; i <len; i++){
		printf("%d\t", str[i]&127);
	}
	
	printf("\nOR:\n");
	for(int i=0; i <len; i++){
		printf("%d\t", str[i]|127);
	}
	
	printf("\nXOR:\n");
	for(int i=0; i <len; i++){
		printf("%d\t", str[i]^127);
	}
	printf("\n");
	
	return 0;
}
