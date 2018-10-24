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
//import com.google.protobuf.ByteString; 
//import javax.xml.bind.DatatypeConverter;  

public class TestTwoFish256{
    public static void main(String [] args) throws Exception {
    Random rand = new Random();
    int DataNumber = 14;
    int LongKeyByte = 32;
    int LongNounceAndCounter = 16;
    byte[] plain = new byte[DataNumber];
    byte[] keyBytes = new byte[LongKeyByte];
    byte[] nonceAndCounter = new byte[LongNounceAndCounter];
    Twofish twofish = new Twofish();

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
    Object keyObject = twofish.makeKey(keyBytes, 16);

    byte[] encrypted = new byte[DataNumber];
    for (int i=0; i<Array.getLength(plain); i+=16) {
        twofish.encrypt(plain, 0, encrypted, 0, keyObject, 16);
    }
    
    //Print encrypted data
    System.out.println("Encrypted data:");
    for(int i = 0; i < encrypted.length; i++){
        System.out.print(encrypted[i] + " ");
    }
    System.out.println("");

    Object keydecObject = twofish.makeKey(keyBytes, 16);
    byte[] decrypted = new byte[DataNumber];

    for (int i=0; i<Array.getLength(encrypted); i+=16) {
        twofish.decrypt(encrypted, i, decrypted, i, keydecObject, 16);
    }
    
    
    //Print decrypted data
    System.out.println("Decrypted data:");
    for(int i = 0; i < decrypted.length; i++){
        System.out.print(decrypted[i] + " ");
    }
    System.out.println("");
    }

}