import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Security;

 import javax.crypto.Cipher;  
 import javax.crypto.SecretKey;  
 import javax.crypto.spec.IvParameterSpec;  
 import javax.crypto.spec.SecretKeySpec;  
 import javax.xml.bind.DatatypeConverter;  

/**
 * Basic IO example with CTR using AES
 */
public class TestAES {
  public static void main(String[] args) throws Exception {
      String hexKey = "YOUR_KEY";  
      String hexIv = "YOUR_IV";  
      String text = "Sample text";  
      String hexEncodedText = string2Hex(text);//convert it to HEX  
   
      //create AES key  
      SecretKey key = new SecretKeySpec(DatatypeConverter.parseHexBinary(hexKey), "AES");  
        
      //create AES IV  
      IvParameterSpec ivspec = new IvParameterSpec(DatatypeConverter.parseHexBinary(hexIv));  
   
      //define cipher mode  
      Cipher cipher = Cipher.getInstance("AES/CTR/NOPADDING");  
      cipher.init(Cipher.ENCRYPT_MODE, key, ivspec);  
   
      //encode  
      byte[] result = cipher.doFinal(DatatypeConverter.parseHexBinary(hexEncodedText));  
   
      String out = DatatypeConverter.printHexBinary(result);  
      String hexOut=string2Hex(out);  
      //print out the encrypted text  
      System.out.println(hex2String(hexOut));  
    }


 public static String string2Hex(String str){  
         
       char[] chars = str.toCharArray();  
    
       StringBuffer hex = new StringBuffer();  
       for(int i = 0; i < chars.length; i++){  
           hex.append(Integer.toHexString((int)chars[i]));  
       }  
    
       return hex.toString();  
  }  
   
public static String hex2String(String hex){  
         
       StringBuilder sb = new StringBuilder();  
         
       /*two hex characters for each ASCII one*/  
       for( int i=0; i<hex.length()-1; i+=2 ){  
    
            String output = hex.substring(i, (i + 2));  
            int decimal = Integer.parseInt(output, 16);  
            sb.append((char)decimal);  
       }  
    
       return sb.toString();  
  } 
}


           
       




