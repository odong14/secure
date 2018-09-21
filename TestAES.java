import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Security;

import java.util.Random;
import java.lang.reflect.Array;

import javax.crypto.Cipher;  
import javax.crypto.SecretKey;  
import javax.crypto.spec.IvParameterSpec;  
import javax.crypto.spec.SecretKeySpec;  
import javax.xml.bind.DatatypeConverter;  

public class TestAES {

  public static void main(String[] args) throws Exception {
    Random rand = new Random();
    int DataNumber = 32;
    int LongKeyByte = 32;
    byte[] plain = new byte[DataNumber];
    byte[] key = new byte[LongKeyByte];
    
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

    byte[] encryptedData = AESEncryption.encrypt(key,plain);

    //Print encrypted data 
    System.out.println("encrypted:");
    for(byte i = 0; i < encryptedData.length; i++){
        System.out.print(encryptedData[i] + " ");
    }
    System.out.println("");

    byte[] decryptedData = AESEncryption.decrypt(key,encryptedData);
    
    //Print encrypted data 
    System.out.println("decrypted:");
    for(byte i = 0; i < decryptedData.length; i++){
      System.out.print(decryptedData[i] + " ");
    }
    System.out.println("");
  } 
 
}


           
       




