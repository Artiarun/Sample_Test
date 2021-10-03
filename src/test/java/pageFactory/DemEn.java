package pageFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class DemEn
{
    private static final String key = "F#G%A)_C(lD/@DQ&";

    public DemEn() {
    }

    public static String Encryption(String unencryptedString) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        MessageDigest md = MessageDigest.getInstance("md5");
        //byte[] digestOfPassword = md.digest("F#G%A)_C(lD/@DQ&".getBytes(StandardCharsets.UTF_8));
        byte[] digestOfPassword = md.digest(key.getBytes(StandardCharsets.UTF_8));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        int j = 0;

        for(int var5 = 16; j < 8; keyBytes[var5++] = keyBytes[j++]) {
        }

        SecretKey secretKey = new SecretKeySpec(keyBytes, "DESede");
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(1, secretKey);
        byte[] plainTextBytes = unencryptedString.getBytes(StandardCharsets.UTF_8);
        byte[] buf = cipher.doFinal(plainTextBytes);
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] base64Bytes = encoder.encode(buf);
        return new String(base64Bytes);
    }

    public static String Decryption(String encryptedString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (encryptedString == null) {
            return "";
        } else {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] message = decoder.decode(encryptedString.getBytes(StandardCharsets.UTF_8));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(key.getBytes(StandardCharsets.UTF_8));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            int j = 0;

            for(int var7 = 16; j < 8; keyBytes[var7++] = keyBytes[j++]) {
            }

            SecretKey secretKey = new SecretKeySpec(keyBytes, "DESede");
            Cipher decipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            decipher.init(2, secretKey);
            byte[] plainText = decipher.doFinal(message);
            return new String(plainText, StandardCharsets.UTF_8);
        }
    }
}
