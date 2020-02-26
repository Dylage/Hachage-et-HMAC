package fr.unilim.iut.cryptanalyse;

import java.util.Scanner;

/**
 * TP3
 */
public class TP3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenue dans ce module de hachage");
        System.out.println("1 - Hacher");
        System.out.println("2 - Trouver collisions");
        // System.out.println("3 - Cryptanalyse (Kasiski)");
        System.out.print("Votre choix : ");

        int choix = sc.nextInt();

        String clearText;
        byte[] hashedText;

        

        switch (choix) {
            case 1:
                System.out.println("Veuillez entrer le texte à hacher :");
                clearText = sc.next();
        
                hashedText = Hachage.hachage(clearText);
                
                System.out.println("Texte haché : " + hashedText);
                break;
            case 2:
                System.out.println("Veuillez entrer le nombre de bits (entre 1 et 16) :");

                int nbBits = sc.nextInt();

                if (nbBits > 16) {
                    System.err.println("Trop de bits pour le programme...");
                    break;
                }

                Hachage.findCollision("ods5.txt", nbBits);
                        
                break;
            case 3:
                // System.out.println("Veuillez entrer le texte à analyser :");
                // crypted = sc.next();
                // int[] tailles = Kasiski.estimateKeySize(crypted);
                // System.out.println("\nTaille probable de la clef : " + tailles[0] + " ou " + tailles[1]);

                break;
        
            default:
                System.err.println("Mauvaise proposition !");
                break;
        }

        System.out.println("\n\nÀ votre service\nAu revoir...");
        sc.close();

    }

}