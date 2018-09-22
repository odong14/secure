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

public class TestGenerateSK{
    public static void main(String argv[]) throws Exception{
        Random rand = new Random();
        int DataNumber = 10;
        int LongKeyByte = 32;
        int LongNounceAndCounter = 16;
        byte[] plain = new byte[DataNumber];
        byte[] keyBytes = new byte[LongKeyByte];
        byte[] nonceAndCounter = new byte[LongNounceAndCounter];
    
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

        byte[] SessionK = GenerateSessionK.generate(keyBytes,plain);
        System.out.println("SHA256:");
        for(int i = 0; i < SessionK.length; i++){
            System.out.print(SessionK[i] + " ");
        }
        System.out.println("");

    }
}