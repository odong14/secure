import java.lang.reflect.Array;
import java.util.Arrays;

public class NonceCnt{
    public static byte[] generate(int cnt, byte[] Nonce){
        int longNonceAndCnt = 16;
        
        byte[] nonceandcnt = new byte[longNonceAndCnt];
        byte[] cntbyte = reversePosition(intToByteArray(cnt));

        Arrays.fill(nonceandcnt, (byte) 0);
        System.arraycopy(cntbyte, 0, nonceandcnt, 0, 4);
        System.arraycopy(Nonce, 0, nonceandcnt, 4, 12);

        return nonceandcnt; 
    }

    public static int getCnt(byte[] nonceCnt){
        byte[] cntByte = new byte[4];
        int cnt;
        System.arraycopy(nonceCnt, 0, cntByte, 0, 4);
        cntByte = reversePosition(cntByte);
        cnt = byteArrayToInt(cntByte);
        return cnt;
    }

    public static byte[] getNonce(byte[] nonceCnt){
        byte[] nonce = new byte[12];
        System.arraycopy(nonceCnt, 4, nonce, 0, 12);
        return nonce;
    }

    private static final byte[] intToByteArray(long value) {
    return new byte[] {
            (byte)(value >>> 24),
            (byte)(value >>> 16),
            (byte)(value >>> 8),
            (byte)value};
    }

    private static final int byteArrayToInt(byte[] value) {
        return value[0] << 24 | (value[1] & 0xFF)  << 16 | (value[2] & 0xFF)  << 8 | (value[3] & 0xFF); 
    }

    private static byte[] reversePosition(byte[] data){
        byte[] temp = new byte[data.length];
        int cnt = data.length - 1;

        for(byte i = 0; i<data.length ;i++){
            temp[cnt - i] = data[i];
        }
        return temp;
    }

}