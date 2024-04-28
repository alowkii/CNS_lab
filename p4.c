//Hill Cipher
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define text_size 50

void mat_to_list(int *key_mat, int *key, int rows, int cols) {
    // Transpose the key matrix
    int t_key_mat[rows][cols];
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            t_key_mat[j][i] = *(key_mat + i * cols + j);
        }
    }
    
    // Transposed matrix into a linear array
    int pos = 0;
    for (int i = 0; i < cols; i++) {
        for (int j = 0; j < rows; j++) {
            *(key + pos++) = t_key_mat[i][j];
        }
    }
}

int main(){
	int rows, cols;
	char ptext[text_size], pad_char='x';
	printf("Enter the order of key:");
	scanf("%d", &rows);
	cols=rows;
	
	//Key matrix
	int key_mat[rows][cols];
	printf("\nEnter the key values:");
	for(int row=0; row<rows; row++){
		for(int col=0; col<cols; col++){
			printf("\na%d%d\tvalue=", row+1, col+1);
			scanf("%d",&key_mat[row][col]);
		}
	}
	//Clears input buffer
	while (getchar() != '\n');
	
	/*
	printf("\n1.Encrypt\n2.Decrypt\n0.Exit\n");
	int opt;
	printf("\nEnter option:");
	scanf("%d",&opt);
	
	//Clears input buffer
	while (getchar() != '\n');
	
	if(opt==2){
		invertMatrix(&key_mat[0][0], rows);
	}else if(opt==0){
		return 0;
	}
	*/
	
	//Get plain text
	printf("\nEnter the plain text:");
	fgets(ptext, sizeof(ptext),stdin);
	int ptext_len = strlen(ptext);
	ptext[--ptext_len] = '\0';
	
	//Padding the text
	while(ptext_len % cols != 0){
		ptext[ptext_len++] = pad_char;
	}
	ptext[ptext_len] = '\0';
	
	//Convert 2d matrix to a suitbale array
	int key[rows*cols];
	mat_to_list(&key_mat[0][0], key, rows, cols);
	
	//Infusing with key in ptext
	char sub_char[rows], org_char[rows], ctext[ptext_len];
	for(int i=0; i<ptext_len; i+=rows){
		for(int j=0;j<rows;j++){
			org_char[j]=ptext[i+j];
		}
		
		int itr=0;
		for(int j=0;j<rows*cols;j+=rows){
			int val=0;
			itr=itr==rows?0:itr;
			for(int k=0;k<rows;k++){
				val+=(org_char[k]-'a')*key[j+k];
			}
			sub_char[itr++] = val%26 + 'a';		
		}
		for(int j=0;j<rows;j++){
			ctext[i+j]=sub_char[j];
		}
	}
	ctext[ptext_len] = '\0';
	
	printf("Required text is: %s",ctext);
		
	
	return 0;
}
