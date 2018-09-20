import java.util.Random;
import java.lang.reflect.Array;

public class TestEncDec {
    
    public static void main(String argv[]){
        Random rand = new Random();
        int DataNumber = 20;
        int LongKey = 32;
        byte[] plain = new byte[DataNumber];
        byte[] key = new byte[LongKey];
        byte[] encryptedData;
        byte[] decryptedData;
        
        
        //Generate dummy data
        for(byte i = 0; i < DataNumber; i++){
            plain[i] = (byte)(rand.nextInt(256) - 128);
        }
        System.out.println("Plainaa data:");
        for(byte i = 0; i < DataNumber; i++){
            System.out.print(plain[i] + " ");
        }
        System.out.println("");

        byte normalizeA[] = new byte[DataNumber];

        byte[] normal = Normalize.enc(plain);
        System.out.println("Normalize data:");
        for(byte i = 0; i < Array.getLength(normal); i++){
            System.out.print(normal[i] + " ");
        }
        System.out.println("");

        byte[] denormal = Normalize.dec(normal,DataNumber);
        System.out.println("Denormal data:");
        for(byte i = 0; i < Array.getLength(denormal); i++){
            System.out.print(denormal[i] + " ");
        }
        System.out.println("");

        //Generate dummy key 
        for(byte i = 0; i < LongKey; i++){
            key[i] = (byte)(rand.nextInt(256) - 128);
        }
        System.out.println("key:");
        for(byte i = 0; i < LongKey; i++){
            System.out.print(key[i] + " ");
        }
        System.out.println("");


    }
}