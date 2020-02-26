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

    /**
     * Méthode pour trouver des collisions dans un dictionnaire
     * @param filePath : lien vers le dictionnaire
     * @param numberOfBits : nombre de bits max à comparer
     */
    public static void findCollision(String filePath, int numberOfBits) {
        BufferedReader br;
        String line;

        try {
            br = new BufferedReader(new FileReader(filePath));
            
            HashMap<Integer, String> hm = new HashMap<>();

            // Sur toutes les lignes
            while ((line = br.readLine()) != null) {
                int hash;

                // On hache avec la méthode selon le nombre de bits que l'on veut (max 16)
                if (numberOfBits > 8) {
                    hash = Hachage.hachage(line)[0] * (int) Math.pow(2, 8)
                         + Hachage.hachage(line)[1] % (1 << numberOfBits);
                }else{
                    hash = Hachage.hachage(line)[0] % (1 << numberOfBits);
                }
                // S'il y est déjà, il y a collision
                if (hm.containsKey(hash)) {
                    System.err.println("Collision : " + line + " et " + hm.get(hash));
                    System.err.println("Nombres de lignes avant collision : " + hm.size());
                    break;
                }
                // Sinon on l'y ajoute
                hm.put(hash, line);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode pour trouver une collision entre un mot et un dictionnaire
     * @param filePath : adresse du dictionnaire
     * @param word : mot à hacher puis comparer
     * @param numberOfBits : nombre de bits à comparer
     */
    public static void findCollisionWithHashed(String filePath, String word, int numberOfBits) {
        BufferedReader br;
        String line;

        try {
            br = new BufferedReader(new FileReader(filePath));
            int hash;
            int i = 0;

            HashMap<Integer, String> hm = new HashMap<>();

            // On hache avec la méthode selon le nombre de bits que l'on veut (max 16)
            if (numberOfBits > 8) {
                hash = Hachage.hachage(word)[0] * (int) Math.pow(2, 8)
                     + Hachage.hachage(word)[1] % (1 << numberOfBits);
            }else{
                hash = Hachage.hachage(word)[0] % (1 << numberOfBits);
            }
            hm.put(hash, word);
            
            // Sur toutes les lignes
            while ((line = br.readLine()) != null) {
                // On hache avec la méthode selon le nombre de bits que l'on veut (max 16)
                if (numberOfBits > 8) {
                    hash = Hachage.hachage(line)[0] * (int) Math.pow(2, 8)
                         + Hachage.hachage(line)[1] % (1 << numberOfBits);
                }else{
                    hash = Hachage.hachage(line)[0] % (1 << numberOfBits);
                }

                // S'il y est déjà, il y a collision
                if (hm.containsKey(hash)) {
                    System.err.println("Collision : " + line + " et " + hm.get(hash));
                    System.err.println("Nombres de lignes avant collision : " + i);
                    break;
                }
                i++;
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}