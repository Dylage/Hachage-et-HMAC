package fr.unilim.iut.cryptanalyse;

import java.util.Scanner;

/**
 * TP3
 */
public class TP3 {

    public static void main(String[] args) {

        // Scanner sc = new Scanner(System.in);
        // System.out.println("Entrez le message à hacher");
        // String msg = sc.nextLine();

        // System.out.println(Hachage.hachage("test"));

        Hachage.findCollision("ods5.txt");
    }
}