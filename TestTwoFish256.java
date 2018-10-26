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

public class TestTwoFish256{
    public static void main(String [] args) throws Exception {
    Random rand = new Random();
    int DataNumber = 16;
    int LongKeyByte = 32;
    int LongNounceAndCounter = 16;
    int blockSize = 16; 
    int blockSizePad;
    int lengthBlock;
    int offset;
    byte[] plain = new byte[DataNumber];
    byte[] keyBytes = new byte[LongKeyByte];
    byte[] nonceAndCounter = new byte[LongNounceAndCounter];
    Twofish twofish = new Twofish();
    PKCS7 padding = new PKCS7();
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
    Object keyObject = twofish.makeKey(keyBytes, blockSize);
    
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

    if(plain.length % blockSize == 0){
        lengthBlock = plain.length / 16;
    } else {
        lengthBlock = (plain.length / 16) + 1;
    }

    //Add padding if need
    //Teu dipake
    padding.init(blockSize);
    byte pad[] = padding.pad(plain, 0, plain.length);
    byte pt[] = new byte[plain.length + pad.length];
    System.arraycopy(plain, 0, pt, 0, plain.length);
    System.arraycopy(pad, 0, pt, plain.length, pad.length);

    offset = 0;
    byte[] encrypted = new byte[plain.length];
    for (int i=0; i<Array.getLength(plain); i+=blockSize) {
        nonceAndCounter = noncent.generate(offset+i,nonce);
        twofish.encrypt(nonceAndCounter, 0, encrypted, 0, keyObject, blockSize);
    }
    
    //Print encrypted data
    System.out.println("Encrypted data:");
    for(int i = 0; i < encrypted.length; i++){
        System.out.print(encrypted[i] + " ");
    }
    System.out.println("");

    //Xor data
    for(int i = 0; i < encrypted.length; i++){
        encrypted[i] = (byte)(encrypted[i] ^ plain[i]);
    }

    //Print encrypted data
    System.out.println("XOR Encrypted data:");
    for(int i = 0; i < encrypted.length; i++){
        System.out.print(encrypted[i] + " ");
    }
    System.out.println("");

    Object keydecObject = twofish.makeKey(keyBytes, 16);
    byte[] decrypted = new byte[encrypted.length];
    
    offset = 0;
    for (int i=0; i<Array.getLength(encrypted); i+=16) {
        nonceAndCounter = noncent.generate(offset+i,nonce);
        twofish.encrypt(nonceAndCounter, i, decrypted, i, keydecObject, 16);
    }

    //Unpad ngga dipake juga
    /*
    int unpad = padding.unpad(decrypted, 0, decrypted.length);
    byte[] output = new byte[decrypted.length - unpad];
    
    System.arraycopy(decrypted, 0, output, 0, output.length);
    */

    //Print decrypted data
    System.out.println("Decrypted data:");
    for(int i = 0; i < decrypted.length; i++){
        System.out.print(decrypted[i] + " ");
    }
    System.out.println("");

    //Xor data
    for(int i = 0; i < encrypted.length; i++){
        decrypted[i] = (byte)(encrypted[i] ^ decrypted[i]);
    }
    
    //Print decrypted data
    System.out.println("XOR Decrypted data:");
    for(int i = 0; i < decrypted.length; i++){
        System.out.print(decrypted[i] + " ");
    }
    System.out.println("");
    }

}
