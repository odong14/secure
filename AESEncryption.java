import java.nio.ByteBuffer;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by User on 09/15/2018.
 */

public class AESEncryption {
    public static byte[] encrypt(byte[] key, byte[] plaintext) throws IllegalArgumentException {
        SecureRandom secureRandom = new SecureRandom();
        Cipher cipher;
        if (key.length < 16) {
            throw new IllegalArgumentException("Key length must be at least 16");
        }
        try {
            byte[] ptnormalized = Normalize.enc(plaintext);
            SecretKey skey = new SecretKeySpec(key, "AES");
            byte[] iv = new byte[12];
            secureRandom.nextBytes(iv);
            cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv);
            cipher.init(Cipher.ENCRYPT_MODE, skey, gcmParameterSpec);
            byte[] encrypted = cipher.doFinal(ptnormalized);
            ByteBuffer buff = ByteBuffer.allocate(1 + iv.length + encrypted.length);
            buff.put((byte) iv.length);
            buff.put(iv);
            buff.put(encrypted);
            return buff.array();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(byte[] key, byte[] encryptedtext) throws IllegalArgumentException {
        if (key.length < 16) {
            throw new IllegalArgumentException("Key length must be at least 16");
        }
        try {
            ByteBuffer buff = ByteBuffer.wrap(encryptedtext);
            int ivLength = buff.get();
            if (ivLength < 12 || ivLength >= 16) {
                throw new IllegalArgumentException("iv length is invalid.");
            }
            byte[] iv = new byte[ivLength];
            buff.get(iv);
            byte[] ciphertext = new byte[buff.remaining()];
            buff.get(ciphertext);
            final Cipher decrypt = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv);
            decrypt.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), gcmParameterSpec);
            byte[] plaintext = decrypt.doFinal(ciphertext);
            return plaintext;
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }


}
