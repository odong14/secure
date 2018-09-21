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
import javax.xml.bind.DatatypeConverter;  

public class TestAESCTR{
    public static void main(String [] args) throws Exception {
    Random rand = new Random();
    int DataNumber = 32;
    int LongKeyByte = 32;
    int LongNounceAndCounter = 16;
    byte[] plain = new byte[DataNumber];
    byte[] key = new byte[LongKeyByte];
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
        key[i] = (byte)(rand.nextInt(256) - 128);
    }
    System.out.println("key:");
    for(byte i = 0; i < LongKeyByte; i++){
        System.out.print(key[i] + " ");
    }
    System.out.println("");


    //Generate dummy nonceAndCounter 
    Arrays.fill(nonceAndCounter, (byte) 0);

    System.out.println("Nonce and Counter:");
    for(byte i = 0; i < LongNounceAndCounter; i++){
        System.out.print(nonceAndCounter[i] + " ");
    }
    System.out.println("");

    }
}