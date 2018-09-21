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
import javax.xml.bind.DatatypeConverter;  

public class TestAESCTR{
    public static void main(String [] args) throws Exception {
    Random rand = new Random();
    int DataNumber = 32;
    int LongKeyByte = 32;
    int LongNounceAndCounter = 16;
    byte[] plain = new byte[DataNumber];
    byte[] keyBytes = new byte[LongKeyByte];
    byte[] nonceAndCounter = new byte[LongNounceAndCounter];
    
    //Generate dummy data
    for(byte i = 0; i < DataNumber; i++){
        plain[i] = (byte)(rand.nextInt(256) - 128);
    }
    System.out.println("Plain data:");
    for(byte i = 0; i < DataNumber; i++){
        System.out.print(plain[i] + " ");
    }
    System.out.println("");

    //Generate dummy key 
    for(byte i = 0; i < LongKeyByte; i++){
        keyBytes[i] = (byte)(rand.nextInt(256) - 128);
    }
    System.out.println("key:");
    for(byte i = 0; i < LongKeyByte; i++){
        System.out.print(keyBytes[i] + " ");
    }
    System.out.println("");


    //Generate dummy nonceAndCounter 
    Arrays.fill(nonceAndCounter, (byte) 0);

    System.out.println("Nonce and Counter:");
    for(byte i = 0; i < LongNounceAndCounter; i++){
        System.out.print(nonceAndCounter[i] + " ");
    }
    System.out.println("");

    SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
    IvParameterSpec ivSpec = new IvParameterSpec(nonceAndCounter);
    Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");

    // encryption pass
    cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
    ByteArrayInputStream bIn = new ByteArrayInputStream(plain);
    CipherInputStream cIn = new CipherInputStream(bIn, cipher);
    ByteArrayOutputStream bOut = new ByteArrayOutputStream();

    int ch;
    while ((ch = cIn.read()) >= 0) {
      bOut.write(ch);
    }

    byte[] cipherText = bOut.toByteArray();
    byte[] encrypted = bOut.toByteArray();

    //Print encrypted data
    System.out.println("Encrypted data:");
    for(byte i = 0; i < encrypted.length; i++){
        System.out.print(encrypted[i] + " ");
    }
    System.out.println("");

    // decryption pass
    cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
    bOut = new ByteArrayOutputStream();
    CipherOutputStream cOut = new CipherOutputStream(bOut, cipher);
    cOut.write(cipherText);
    cOut.close();
    
    byte[] decrypted = bOut.toByteArray();

    //Print decrypted data
    System.out.println("Decrypted data:");
    for(byte i = 0; i < decrypted.length; i++){
        System.out.print(decrypted[i] + " ");
    }
    System.out.println("");
    }
}