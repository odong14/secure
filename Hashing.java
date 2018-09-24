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
        Sha512 hash512 = new Sha512();
        Sha256 hash256 = new Sha256();

        int panjangBalikan = 16;
        int panjanghA = hA.length;
        int panjangSavedKM = SavedKM.length;
        int panjangMasukanHash = panjanghA + panjangSavedKM;

        byte[] balikan = new byte[panjangBalikan];
        byte[] MasukanHash = new byte[panjangMasukanHash];

        hash256.update(SavedKM, 0, SavedKM.length);
        byte[] digestSavedKM = hash256.digest();
    
        System.arraycopy(digestSavedKM,0,MasukanHash,0,digestSavedKM.length);
        System.arraycopy(hA,0,MasukanHash,digestSavedKM.length,hA.length);
        
        hash512.update(MasukanHash, 0, MasukanHash.length);
        byte[] digest = hash512.digest();
        byte[] Z1 = new byte[panjangBalikan];
        byte[] Z2 = new byte[panjangBalikan];
        byte[] Z3 = new byte[panjangBalikan];
        byte[] Z4 = new byte[panjangBalikan];
        int temp = 0;

        System.arraycopy(digest,temp,Z1,0,panjangBalikan);
        temp += panjangBalikan;
        System.arraycopy(digest,temp,Z2,0,panjangBalikan);
        temp += panjangBalikan;
        System.arraycopy(digest,temp,Z3,0,panjangBalikan);
        temp += panjangBalikan;
        System.arraycopy(digest,temp,Z4,0,panjangBalikan);
        
        for(int i = 0; i < panjangBalikan; i++){
            balikan[i] = (byte)(Z1[i] ^ Z2[i] ^ Z3[i] ^ Z4[i]);
        }

        return balikan;
    }
}