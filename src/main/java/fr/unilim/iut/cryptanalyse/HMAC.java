package fr.unilim.iut.cryptanalyse;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/**
 * HMAC
 */
public class HMAC {

    /**
     * Méthode pour hacher en HMAC-SHA1
     * @param clearedText : texte à hacher
     * @param key : clef de hachage
     * @return : un tableau d'octet, chiffrés
     */
    public static byte[] hmac(String clearedText, String key) {
        byte[] hashedText = null;

        try {
            byte[] byteKey = key.getBytes("UTF-8");

            Key sks = new SecretKeySpec(byteKey, "SHA");

            Mac mac = Mac.getInstance("HmacSHA1");

            mac.init(sks);

            hashedText = mac.doFinal(clearedText.getBytes("UTF-8"));


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return hashedText;
    }

    /**
     * Méthode pour convertir un tableau d'octets en chaine de caractère
     * @param buf : tableau d'octet
     * @return : chaine de caractère
     */
    public static String toHex(byte [] buf) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;
        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }
}