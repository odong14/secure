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

public class TestHashingKS {
    public static void main(String[] args) throws Exception{
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

        byte[] hased = Hashing.hashingKS(plain);

        //Print hashed data
        System.out.println("Test Hashing KS");
        System.out.println("Hashed data:");
        for(int i = 0; i < hased.length; i++){
            System.out.print(hased[i] + " ");
        }
        System.out.println("");
        System.out.println("Panjang hashed :" + hased.length + "byte");

        
        byte[] hasedKM = Hashing.hashingKM(hased,plain);

        //Print hashed data
        System.out.println("Test Hashing KM");
        System.out.println("Hashed data:");
        for(int i = 0; i < hasedKM.length; i++){
            System.out.print(hasedKM[i] + " ");
        }
        System.out.println("");
        System.out.println("Panjang hashed :" + hasedKM.length + "byte");
    }
}