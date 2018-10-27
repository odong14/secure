import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Security;

import java.util.Random;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.crypto.Cipher;  
import javax.crypto.SecretKey;  
import javax.crypto.spec.IvParameterSpec;  
import javax.crypto.spec.SecretKeySpec;  
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

public class TestTwoFishCTR{
    public static void main(String [] args) throws Exception {
    Random rand = new Random();
    int DataNumber = 15;
    int LongKeyByte = 32;
    int LongNounceAndCounter = 16;
    int blockSize = 16; 
    int blockSizePad;
    int lengthBlock;
    int offset;
    byte[] plain = new byte[DataNumber];
    byte[] keyBytes = new byte[LongKeyByte];
    TwofishCTR twofishCtr = new TwofishCTR();
    NonceCnt noncent = new NonceCnt();

    //Generate dummy data
    for(int i = 0; i < DataNumber; i++){
        plain[i] = (byte)(rand.nextInt(256) - 128);
    }
    System.out.println("Plain data:");
    for(int i = 0; i < DataNumber; i++){
        System.out.print(plain[i] + " ");
    }
    System.out.println("");

    //Generate dummy key 
    for(int i = 0; i < LongKeyByte; i++){
        keyBytes[i] = (byte)(rand.nextInt(256) - 128);
    }
    System.out.println("key:");
    for(int i = 0; i < LongKeyByte; i++){
        System.out.print(keyBytes[i] + " ");
    }
    System.out.println("");
    
    //Generate dummy nonce
    int longnonce = 12;
    byte[] nonce = new byte[longnonce];

    for(byte i = 0; i < longnonce; i++){
        nonce[i] = (byte)(rand.nextInt(256) - 128);
    }
    System.out.println("nonce:");
    for(int i = longnonce - 1; i > -1; i--){
        System.out.print(nonce[i] + " ");
    }
    System.out.println("");
    
    byte[] encrypted = twofishCtr.encrypt(keyBytes, plain, nonce, 1);

    //Print encrypted data
    System.out.println("Encrypted data:");
    for(int i = 0; i < encrypted.length; i++){
        System.out.print(encrypted[i] + " ");
    }
    System.out.println("");

    byte[] decrypted = twofishCtr.decrypt(keyBytes, encrypted);
    //Print decrypted data
    System.out.println("Decrypted data:");
    for(int i = 0; i < decrypted.length; i++){
        System.out.print(decrypted[i] + " ");
    }
    System.out.println("");

    }

}
