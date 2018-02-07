#include "stdafx.h"
#include "AES.h"

void print(unsigned char* state);

int main(int argc, char* argv[])
{
    unsigned char input[] =
    {
        0x11, 0x22, 0x33, 0x44,
        0x55, 0x66, 0x77, 0x88,
        0x99,0x00,0xAA, 0xBB,
        0xCC,0xDD, 0xEE,  0xFF
    };
    
    unsigned char key[] =
    {
        0x13,  0x57,  0x9B,  0xDF,
        0x02, 0x46,  0x8A, 0xCE,
        0x12,  0x34,  0x56, 0x78,
        0x90, 0xAB,  0xCD, 0xEF
    };
    AES aes(key);
    
    printf("明文: ");
    print(input);
    
    aes.Cipher(input);
    printf("密文: ");
    print(input);
    
    aes.InvCipher(input);
    printf("解密: ");
    print(input);
    
    //	int j;
    //	printf("\n");
    //	char str[32] = "Hello,My AES Cipher!";
    //	for(j=0; j<32; j++)printf("%X ",(unsigned char)str[j]);
    //	printf("\n");
    //	aes.Cipher((void *)str);
    //	for(j=0; j<32; j++)printf("%X ",(unsigned char)str[j]);
    //	printf("\n");
    //	aes.InvCipher((void *)str,21);
    //	for(j=0; j<32; j++)printf("%X ",(unsigned char)str[j]);
    //	printf("\n");
    return 0;
}

void print(unsigned char* state)
{
    int i;
    for(i=0; i<16; i++)
    {
        printf("%s%X ",state[i]>15 ? "" : "0", state[i]);//自动补0
    }
    printf("\n");
}

