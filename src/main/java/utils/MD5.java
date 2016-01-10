package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Alex, 1/10/2016.
 */
public class MD5 {

    public static String encrypt(final String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());

            final byte byteData[] = messageDigest.digest();

            // Convert the bytes to hex
            StringBuilder stringBuilder = new StringBuilder();
            for (byte aByteData : byteData) {
                final String hex = Integer.toHexString(0xff & aByteData);
                if (hex.length() == 1) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(hex);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean matches(final String password, final String md5Hash) {
        return encrypt(password).equals(md5Hash);
    }
}
