import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.InvalidKeyException;

import java.lang.reflect.Array;
import java.util.Arrays;

public class UpdateKS{
    public static byte[] hashing512(byte[] KSaved, byte[] KSession) throws Exception{
        Sha512 hash512 = new Sha512();
        int panjangData = KSaved.length;
        byte[] xored = new byte[panjangData];

        for(int i = 0;i < panjangData;i++){
            xored[i] = (byte)(KSaved[i] ^ KSession[i]);
        }

        hash512.update(xored, 0, panjangData);
        byte[] digest = hash512.digest();
        
        byte[] hl = new byte[panjangData];
        byte[] hr = new byte[panjangData];

        System.arraycopy(digest,0,hr,0,panjangData);
        System.arraycopy(digest,32,hl,0,panjangData);

        for(int i = 0;i < panjangData;i++){
            xored[i] = (byte)(hl[i] ^ hr[i]);
        }
    
        return xored;
    }

    public static boolean save(byte[] in){

        return true;
    }
}