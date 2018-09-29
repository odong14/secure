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
//import javax.xml.bind.DatatypeConverter;  

public class AESCTR {
    public static byte[] encrypt(byte [] keyBytes, byte [] plain, byte[] nonceAndCounter)throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(nonceAndCounter);
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        int panjangPlain = plain.length;
        int panjangNnCnt = nonceAndCounter.length;
        int panjangNP    = panjangPlain + panjangNnCnt;
        byte[] IVPencrypted = new byte[panjangNP];
        
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        ByteArrayInputStream bIn = new ByteArrayInputStream(plain);
        CipherInputStream cIn = new CipherInputStream(bIn, cipher);

        int ch;
        while ((ch = cIn.read()) >= 0) {
            bOut.write(ch);
        }
        
        System.arraycopy(bOut.toByteArray(),0,IVPencrypted,panjangNnCnt,panjangPlain);
        System.arraycopy(nonceAndCounter,0,IVPencrypted,0,panjangNnCnt);

        return IVPencrypted;
    }

    public static byte[] decrypt(byte [] keyBytes, byte [] IVPencrypted) throws Exception{
        int panjangNnCnt = 16;
        int panjangEncd  = IVPencrypted.length - panjangNnCnt;
        byte[] encrypted = new byte[panjangEncd];
        byte[] nonceAndCounter = new byte[panjangNnCnt];

        System.arraycopy(IVPencrypted,0,nonceAndCounter,0,panjangNnCnt);
        System.arraycopy(IVPencrypted,panjangNnCnt,encrypted,0,panjangEncd);

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

    final protected static char[] hexArray = "0123456789abcdef".toCharArray();
	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for ( int j = 0; j < bytes.length; j++ ) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
    }
    public static String viewKS(byte[] key){
        byte panjangView = 3;
        byte[] temp = new byte[panjangView];
        String balikan; 
        System.arraycopy(key,(key.length - panjangView),temp,0,panjangView);
        balikan = bytesToHex(temp);
        return balikan;
    }
}