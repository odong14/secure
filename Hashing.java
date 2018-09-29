import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.InvalidKeyException;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Hashing{
    public static byte[] hashingKS(byte[] in) throws Exception{
        Sha256 hash256 = new Sha256();
        int panjangBalikan = 16;
        byte[] temp = new byte[in.length];
        byte[] xored = new byte[in.length];
        byte[] digest; 
        byte[] hl = new byte[panjangBalikan];
        byte[] hr = new byte[panjangBalikan];
        int loop = 3;
        int panjangByteR = 32;
        byte[] balikan = new byte[panjangByteR];

        Arrays.fill(temp,(byte)0);

        for(int i=0;i < loop;i++){
            for(int j=0; j < in.length;j++){
                xored[j] = (byte)(in[j] ^ temp[j]);
            }
            hash256.update(xored, 0, xored.length);
            digest = hash256.digest();
            System.arraycopy(digest,0,temp,0,digest.length);  
        }

        System.arraycopy(temp,0,balikan,0,panjangByteR);
        System.arraycopy(balikan,0,hr,0,panjangBalikan);
        System.arraycopy(balikan,panjangBalikan,hl,0,panjangBalikan);

        balikan = new byte[panjangBalikan];

        for(int i=0; i < panjangBalikan;i++){
            balikan[i] = (byte)(hl[i] ^ hr[i]);
        }

        return balikan;
    }

    public static byte[] hashingKM(byte[] hA, byte[] SavedKM) throws Exception{
        Sha256 hash256 = new Sha256();

        int panjangBalikan = 32;
        int panjanghA = hA.length;
        int panjangSavedKM = SavedKM.length;
        int panjangMasukanHash = panjanghA + panjangSavedKM;
        byte[] balikan = new byte[panjangBalikan];
        
        hash256.update(SavedKM, 0, SavedKM.length);
        byte[] SavedKMHashed = hash256.digest();
        
        hash256.update(hA, 0, hA.length);
        byte[] hAHashed = hash256.digest();
    
        for(int i = 0; i < panjangBalikan; i++){
            balikan[i] = (byte)(SavedKMHashed[i] ^ hAHashed[i]);
        }

        return balikan;
    }
}