import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.InvalidKeyException;

public class EncDec{
    
    public void encrypt (byte [] in, byte [] out, byte [] KeySession, int cnt){
        Twofish twofish = new Twofish();
        Object keyObject = twofish.makeKey(KeySession,16);
        
        for (int i=0; i<Array.getLength(in); i+=16){
            twofish.encrypt(1, i, out, i, keyObject, 16);
        }

        
    }

    public void decrypt (byte [] in, byte [] out, byte [] KeySession, int cnt){
        
    }

}