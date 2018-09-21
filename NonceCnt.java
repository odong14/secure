import java.lang.reflect.Array;
import java.util.Arrays;

public class NonceCnt{
    public static byte[] generate(int cnt, byte[] Nonce){
        int longNonceAndCnt = 16;
        
        byte[] nonceandcnt = new byte[longNonceAndCnt];
        byte[] cntbyte = intToByteArray(cnt);

        Arrays.fill(nonceandcnt, (byte) 0);
        System.arraycopy(cntbyte, 0, nonceandcnt, 0, 4);
        System.arraycopy(Nonce, 0, nonceandcnt, 4, 12);

        return nonceandcnt; 
    }

    public static final byte[] intToByteArray(long value) {
    return new byte[] {
            (byte)(value >>> 24),
            (byte)(value >>> 16),
            (byte)(value >>> 8),
            (byte)value};
    }
}