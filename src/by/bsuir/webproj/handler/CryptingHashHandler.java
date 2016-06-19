package by.bsuir.webproj.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Алексей on 10.04.2016.
 */
public class CryptingHashHandler {
    private final static Logger LOGGER = LogManager.getLogger(CryptingHashHandler.class);
    public static String getHash(String str) {
        if(str != null) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.reset();
                messageDigest.update(str.getBytes("utf-8"));
                String tempInt = new BigInteger(1, messageDigest.digest()).toString(16);
                StringBuilder resultBuilder = new StringBuilder(32);

                for (int i = 0, count = 32 - tempInt.length(); i < count; i++) {
                    resultBuilder.append("0");
                }
                return resultBuilder.append(tempInt).toString();
            } catch (NoSuchAlgorithmException e) {
                LOGGER.error("No such algorithm");
                return str;
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("Unsupported encoding");
                return str;
            }
        }
        else
        {
            return new String("");
        }
    }
}
