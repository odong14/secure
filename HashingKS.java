import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.InvalidKeyException;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HashingKS{
    public static byte[] hashing256(byte[] in) throws Exception{
        Sha256 hash256 = new Sha256();
        byte[] temp = new byte[in.length];
        byte[] xored = new byte[in.length];
        byte[] digest; 
        int loop = 3;
        int panjangByteR = 32;
        byte[] balikan = new byte[panjangByteR];

        Arrays.fill(temp,(byte)0);

        for(int i=0;i < loop;i++){
            for(int j=0; i < in.length;i++){
                xored[i] = (byte)(in[i] ^ temp[i]);
            }
            hash256.update(xored, 0, xored.length);
            digest = hash256.digest();
            System.arraycopy(digest,0,temp,0,digest.length);  
        }

        System.arraycopy(temp,0,balikan,0,panjangByteR);
        return balikan;
    }
}