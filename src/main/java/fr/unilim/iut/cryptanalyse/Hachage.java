package fr.unilim.iut.cryptanalyse;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hachage
 */
public class Hachage {

    /**
     * Méthode pour hacher en SHA-1 une chaine de caractere
     * @param str : chaine à hacher
     * @return    : chaine hachée
     */
    public static String hachage(String str) {
        StringBuffer hexString = new StringBuffer();

        byte[] digest;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA");

            md.update(str.getBytes());
            digest = md.digest();

            for (int i = 0; i < digest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & digest[i]));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
         

        return hexString.toString();
    }
}