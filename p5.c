#include<stdio.h>

int IP[] = {2, 6, 3, 1, 4, 8, 5, 7};

int IP_inverse[] = {4, 1, 3, 5, 7, 2, 8, 6};

int S0[4][4] = {
    {1, 0, 3, 2},
    {3, 2, 1, 0},
    {0, 2, 1, 3},
    {3, 1, 3, 2}
};

int S1[4][4] = {
    {0, 1, 2, 3},
    {2, 0, 1, 3},
    {3, 0, 1, 0},
    {2, 1, 0, 3}
};

int P4[] = {2, 4, 3, 1};

int EP[] = {4, 1, 2, 3, 2, 3, 4, 1};

//to rearrange according to permutation
int permut(int ptext, int* perm, int bit, int len) {
    int result = 0;
    for (int i = 0; i < len; i++) {
        result |= ((ptext >> (bit - perm[i])) & 1) << ((len-1) - i);
    }
    return result;
}

//s-box substitution
int s_box_substitute(int value, int s_box[4][4]) {
    int row = ((value & 0b1000) >> 2) | (value & 0b0001);
    int col = (value & 0b0110) >> 1;
    return s_box[row][col];
}

//encrypt cycle
int encrypt_current_cycle(int text, int key){
	int x0 = text >> 4;
	int x1 = text & 0x0F;
	
	text = permut(x1, EP, 4, 8);
	
	text ^= key;
	
	int s0 = text >> 4;
	int s1 = text & 0x0F;
	
	s0 = s_box_substitute(s0, S0);
	s1 = s_box_substitute(s1, S1);
	
	text = (s0<<2) | s1;
	
	text = permut(text, P4, 4, 4);
	
	text = text^x0;
	text = (text<<4) | x1;
	return text;
}

//encrypt everything
int encrypt(int ptext, int k1, int k2){
	//initial permutation
	int text = permut(ptext, IP, 8, 8);
	
	//round 1
	text = encrypt_current_cycle(text, k1);
	
	//swap first 4 bits to last bits
	text = (text>>4) | ((text & 0b00001111)<<4);
	
	//round 2
	text = encrypt_current_cycle(text, k2);
	
	//inverse initial permutation
	text = permut(text, IP_inverse, 8, 8);

	return text;
}

int main(){
	int ptext = 0b11110011;
	int k1 = 0b10100100;
	int k2 = 0b01000011;
	
	int ctext = encrypt(ptext, k1, k2);
	
	printf("Ctext:%x",ctext);
	return 0;
}
