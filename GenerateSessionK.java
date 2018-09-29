import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.InvalidKeyException;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GenerateSessionK {
    public static byte[] generate(byte[] InitSessionK, byte[] SavedSharedK) throws Exception{
        Sha256 hash256 = new Sha256();
        hash256.update(InitSessionK, 0, InitSessionK.length);
        byte[] InitSessionKHashed = hash256.digest();
        int panjangBalikan = 32;
        byte[] xored = new byte[panjangBalikan];

        for(int i=0; i < panjangBalikan; i++){
            xored[i] = (byte)(InitSessionKHashed[i] ^ SavedSharedK[i]);
        }

        hash256.update(xored, 0, xored.length);
        byte[] digest = hash256.digest();

        return digest;
    }
}