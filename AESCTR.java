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

public class AESCTR {
    public static byte[] encrypt(byte [] keyBytes, byte [] plain, byte[] nonceAndCounter)throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(nonceAndCounter);
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        ByteArrayInputStream bIn = new ByteArrayInputStream(plain);
        CipherInputStream cIn = new CipherInputStream(bIn, cipher);

        int ch;
        while ((ch = cIn.read()) >= 0) {
            bOut.write(ch);
        }

        return bOut.toByteArray();
    }

    public static byte[] decrypt(byte [] keyBytes, byte [] encrypted, byte[] nonceAndCounter) throws Exception{
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(nonceAndCounter);
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        ByteArrayOutputStream bOut = new ByteArrayOutputStream(encrypted.length);
        
       cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        bOut = new ByteArrayOutputStream();
        CipherOutputStream cOut = new CipherOutputStream(bOut, cipher);
        cOut.write(encrypted);
        cOut.close();

        return bOut.toByteArray();
    }
}