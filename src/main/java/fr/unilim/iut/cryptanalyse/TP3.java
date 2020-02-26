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
        System.out.println("3 - Trouver collisions avec un mot");
        System.out.println("4 - Hacher un mot avec une clef (HMAC-SHA1)");
        System.out.print("Votre choix : ");

        int choix = sc.nextInt();
        sc.nextLine();

        String clearText = "";
        String key = "";
        byte[] hashedText;
        int nbBits;
        

        switch (choix) {
            case 1:
                System.out.println("Veuillez entrer le texte à hacher :");
                clearText = sc.nextLine();
        
                hashedText = Hachage.hachage(clearText);
                
                System.out.println("Texte haché : " + hashedText);
                break;
            case 2:
                System.out.println("Veuillez entrer le nombre de bits (entre 1 et 16) :");

                nbBits = sc.nextInt();

                if (nbBits > 16) {
                    System.err.println("Trop de bits pour le programme...");
                    break;
                }

                Hachage.findCollision("ods5.txt", nbBits);
                        
                break;
            case 3:

                System.out.println("Veuillez entrer le mot à chercher");

                String word = sc.nextLine();

                System.out.println("Veuillez entrer le nombre de bits (entre 1 et 16) :");

                nbBits = sc.nextInt();

                if (nbBits > 16) {
                    System.err.println("Trop de bits pour le programme...");
                    break;
                }

                Hachage.findCollisionWithHashed("ods5.txt", word, nbBits);

                break;
                
            case 4:
                System.out.println("Veuillez saisir le texte à hacher :");
                clearText = sc.nextLine();

                System.out.println("le texte est : " + clearText);

                System.out.println("Veuillez saisir la clef :");
                key = sc.nextLine();

                hashedText = HMAC.hmac(clearText, key);

                System.out.println(HMAC.toHex(hashedText));

                break;
            default:
                System.err.println("Mauvaise proposition !");
                break;
        }

        System.out.println("\n\nÀ votre service\nAu revoir...");
        sc.close();

    }

}