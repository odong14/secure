import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.InvalidKeyException;

public class TestSHA512 {
public static void main(String argv[]) throws Exception {
String plaintext = "I am text to be hidden away";
String cipher = hash(plaintext);
System.out.println("Hash512 plaint text:");
System.out.println(cipher);
}

public static String hash (String cookieValue) throws InvalidKeyException, UnsupportedEncodingException {

Sha512 hash512 = new Sha512();

byte[] plainText;

// initialize byte arrays for plain/encrypted text
plainText = cookieValue.getBytes("UTF8");

hash512.update(plainText, 0, cookieValue.length());

byte[] digest = hash512.digest();

String hashedString = Base64.encode(digest);
return hashedString;
}
}