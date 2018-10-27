import java.lang.reflect.Array;
import java.util.Arrays;

public class TwofishCTR {
    
    private int blockSize = 16;
    private int LongNounceAndCounter = 16;

    public byte[] encrypt(byte [] keyBytes, byte [] plain, byte[] nonce, int cnt)throws Exception {
        
        int lengthBlock;
        Twofish twofish = new Twofish();
        NonceCnt noncent = new NonceCnt();
        Object keyObject = twofish.makeKey(keyBytes, blockSize);

        if(plain.length % blockSize == 0){
            lengthBlock = plain.length / 16;
        } else {
            lengthBlock = (plain.length / 16) + 1;
        }
        byte[] nonceAndCounter = new byte[LongNounceAndCounter * lengthBlock];
    
        for(int i = 0; i < lengthBlock; i++){
            byte[] temp = noncent.generate(i + cnt,nonce);
            System.arraycopy(temp, 0, nonceAndCounter, i * blockSize, blockSize);
        }

        byte[] encrypted = new byte[nonceAndCounter.length];
        for (int i=0; i<Array.getLength(nonceAndCounter); i+=blockSize) {
            twofish.encrypt(nonceAndCounter, i, encrypted, i, keyObject, blockSize);
        }

        byte[] outXOR = new byte[plain.length];

        //Xor data
        for(int i = 0; i < plain.length; i++){
            encrypted[i] = (byte)(encrypted[i] ^ plain[i]);
            outXOR[i] = encrypted[i];
        }

        byte[] balikan = new byte[outXOR.length + LongNounceAndCounter];
        System.arraycopy(nonceAndCounter, 0, balikan, 0, 16);
        System.arraycopy(outXOR, 0, balikan, LongNounceAndCounter, outXOR.length);

        return balikan;
    }

    public byte[] decrypt(byte [] keyBytes, byte [] IVencrypted) throws Exception {
        
        Twofish twofish = new Twofish();
        NonceCnt noncent = new NonceCnt();

        byte[] encrypted = new byte[IVencrypted.length - LongNounceAndCounter];
        byte[] NonceAndCounter = new byte[LongNounceAndCounter];

        System.arraycopy(IVencrypted, 0, NonceAndCounter, 0, LongNounceAndCounter);
        System.arraycopy(IVencrypted, LongNounceAndCounter, encrypted, 0, encrypted.length);

        byte[] nonce = noncent.getNonce(NonceAndCounter);
        int cnt = noncent.getCnt(NonceAndCounter);

        int lengthBlock;
        Object keyObject = twofish.makeKey(keyBytes, blockSize);

        if(encrypted.length % blockSize == 0){
            lengthBlock = encrypted.length / 16;
        } else {
            lengthBlock = (encrypted.length / 16) + 1;
        }
        byte[] nonceAndCounter = new byte[LongNounceAndCounter * lengthBlock];
        byte[] tempo = new byte[nonceAndCounter.length];

        for(int i = 0; i < lengthBlock; i++){
            byte[] temp = noncent.generate(i + cnt,nonce);
            System.arraycopy(temp, 0, nonceAndCounter, i * blockSize, blockSize);
        }

        for (int i=0; i<Array.getLength(nonceAndCounter); i+=blockSize) {
            twofish.encrypt(nonceAndCounter, i, tempo, i, keyObject, blockSize);
        }

        //Xor data
        for(int i = 0; i < encrypted.length; i++){
            encrypted[i] = (byte)(encrypted[i] ^ tempo[i]);
        }

        return encrypted;
    }

}