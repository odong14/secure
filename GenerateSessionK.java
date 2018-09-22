import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.InvalidKeyException;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GenerateSessionK {
    public static byte[] generate(byte[] InitSessionK, byte[] SavedSharedK) throws Exception{
        int panjangInit = InitSessionK.length;
        int panjangSaved = SavedSharedK.length;
        byte[] xored;
        byte[] temp;
        byte[] yangPendek;
        byte[] yangPanjang;
        int panjangTemp;

        if(panjangInit >= panjangSaved){
            panjangTemp = panjangInit;
            yangPendek = new byte[panjangSaved];
            yangPanjang = new byte[panjangInit];
            System.arraycopy(InitSessionK,0,yangPanjang,0,panjangInit);
            System.arraycopy(SavedSharedK,0,yangPendek,0,panjangSaved);
        } else {
            panjangTemp = panjangSaved;
            yangPendek = new byte[panjangInit];
            yangPanjang = new byte[panjangSaved];
            System.arraycopy(InitSessionK,0,yangPendek,0,panjangInit);
            System.arraycopy(SavedSharedK,0,yangPanjang,0,panjangSaved);
        }

        temp = new byte[panjangTemp];
        xored = new byte[panjangTemp];
        Arrays.fill(temp,(byte)0);
        System.arraycopy(yangPendek,0,temp,0,yangPendek.length);  

        for(int i=0; i < panjangTemp; i++){
            xored[i] = (byte)(temp[i] ^ yangPanjang[i]);
        }

        Sha256 hash256 = new Sha256();
        hash256.update(xored, 0, xored.length);
        byte[] digest = hash256.digest();

        return digest;
    }
}