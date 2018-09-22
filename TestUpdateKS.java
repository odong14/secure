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

public class TestUpdateKS{
    public static void main(String argv[]) throws Exception{
        Random rand = new Random();
        int DataNumber = 52;
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
        System.out.println("KSession:");
        for(int i = 0; i < SessionK.length; i++){
            System.out.print(SessionK[i] + " ");
        }
        System.out.println("");

        plain[1] = (byte)(plain[1] ^ plain[10]);
        byte[] SavedK = GenerateSessionK.generate(keyBytes,plain);
        System.out.println("KSaved:");
        for(int i = 0; i < SavedK.length; i++){
            System.out.print(SavedK[i] + " ");
        }
        System.out.println("");

        byte[] UpdatedKS = UpdateKS.hashing512(SavedK,SessionK);
        System.out.println("UpdatedKSaved:");
        for(int i = 0; i < UpdatedKS.length; i++){
            System.out.print(UpdatedKS[i] + " ");
        }
        System.out.println("");
    }
}