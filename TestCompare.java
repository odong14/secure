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

public class TestCompare{
    public static void main(String[] args){
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

        byte[] temp = new byte[plain.length];
        System.arraycopy(plain,0,temp,0,plain.length);
        temp[2]=12;
        boolean compare = CompareK.compare(plain,temp);
        if(compare){
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }

    }
}