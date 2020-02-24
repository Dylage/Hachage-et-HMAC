package fr.unilim.iut.cryptanalyse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Hachage
 */
public class Hachage {

    private static final int NUMBER_OF_BITS = 8;

    /**
     * Méthode pour hacher en SHA-1 une chaine de caractere
     * 
     * @param str : chaine à hacher
     * @return : chaine hachée
     */
    public static byte[] hachage(String str) {

        byte[] digest = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA");

            md.update(str.getBytes());
            digest = md.digest();

            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return digest;
    }

    public static void findCollision(String filePath) {
        BufferedReader br;
        String line;

        try {
            br = new BufferedReader(new FileReader(filePath));
            
            HashMap<Integer, String> hm = new HashMap<>();
            while ((line = br.readLine()) != null) {
                int hash = Hachage.hachage(line)[0] % (1 << NUMBER_OF_BITS);
                if (hm.containsKey(hash)) {
                    System.err.println("Collision : " + line + " et " + hm.get(hash));
                    System.err.println("Nombres de lignes avant collision : " + hm.size());
                    break;
                }
                hm.put(hash, line);
            }
            br.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}