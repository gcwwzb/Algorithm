import javax.swing.plaf.synth.SynthStyle;

public class RC4{
	
	
	public static int[] myRC4(int[] aInput,int[] aKey) 
    { 
        int[] S = new int[256]; 
        int[] K = new int[256]; 
       
        //S盒初始化，0～255填充
        for (int i=0;i<256;i++) 
            S[i]=i; 
 
        for (int i= 0;i<256;i++) 
        { 
        	//用初始密钥key填充另一个256字节的数组Key,不断重复直到填充完
            K[i]=aKey[i % aKey.length]; 
        } 
        
        
        //初始化S盒
        int  j=0;        
        for (int i=0;i<255;i++) 
        { 
            j=(j+S[i]+K[i]) % 256; 
            int temp = S[i]; //交换Si和Sj
            S[i]=S[j]; 
            S[j]=temp; 
        } 
    
    
        int i=0; 
        j=0; 
        int[] iInputChar = aInput;
        int[] iOutputChar = new int[iInputChar.length]; 
        for(int x = 0;x<iInputChar.length;x++) 
        { 
            i = (i+1) % 256; 
            j = (j+S[i]) % 256; 
            
            int temp = S[i]; //交换Si和Sj
            S[i]=S[j]; 
            S[j]=temp; 
            
            int t = (S[i]+S[j] ) % 256; 
            int iY = S[t]; 
            
            iOutputChar[x] =(byte) (iInputChar[x] ^ iY);
        } 
        
        return iOutputChar; 
                
    }

public static String BytesToString(int[] bytes) {
		
		String result ="";
		for(int i=0;i<bytes.length;i++)
		{
			int temp = bytes[i] & 0xff;
			String tempHex = Integer.toHexString(temp);
			
			if(tempHex.length()<2)
				result += "0"+tempHex;
			
			else result += tempHex;
		}
		
		
		return result;
		
		
	}
	
	public static void main(String[] args) {
		 int[] Text = { 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99,
				   0x00, 0xAA, 0xBB, 0xCC, 0xDD, 0xEE, 0xFF};
		String aInput =BytesToString(Text);
		 int[] keys = {0x13, 0x57, 0x9B, 0xDF, 0x02, 0x46, 0x8A, 0xCE, 0x12,
	        		0x34, 0x56,0x78, 0x90, 0xAB, 0xCD, 0xEF};
		String aKey = keys.toString();
		
		int[] encrypt = myRC4(Text, keys);
		int[] decrypt = myRC4(encrypt, keys);
		System.out.println(aInput);
		System.out.println(BytesToString(encrypt));
		System.out.println(BytesToString(decrypt));
		
	}
}
