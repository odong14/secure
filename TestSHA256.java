import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.InvalidKeyException;

public class TestSHA256 {
public static void main(String argv[]) throws Exception {
String plaintext = "I am text to be hidden away";
String cipher = hash(plaintext);
System.out.println("Hash256 plaint text:");
System.out.println(cipher);
}

public static String hash (String cookieValue) throws InvalidKeyException, UnsupportedEncodingException {

//IMessageDigest md = HashFactory.getInstance("SHA-256");
Sha256 hash256 = new Sha256();

byte[] plainText;

// initialize byte arrays for plain/encrypted text
plainText = cookieValue.getBytes("UTF8");

hash256.update(plainText, 0, cookieValue.length());

byte[] digest = hash256.digest();

String hashedString = Base64.encode(digest);
return hashedString;
}
}