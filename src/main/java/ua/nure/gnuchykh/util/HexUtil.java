package ua.nure.gnuchykh.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * Util class for password hashing.
 * @author qny4ix
 *
 */
public final class HexUtil {

    private HexUtil() {

    }

    private static final Logger LOG = Logger.getLogger(HexUtil.class);

    public static String toHex(final String st) {


        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            LOG.info("Ошибка хеширования " + e);
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String hex = bigInt.toString(16);
        return hex;
    }
}
