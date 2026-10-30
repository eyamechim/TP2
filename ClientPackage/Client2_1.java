package ClientPackage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client2_1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Client connecté.");

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez une opération (ex: 55 * 25).");
        System.out.println("Tapez 0 pour quitter.");

        while (true) {
            System.out.print("\nOpération > ");
            String ligne = scanner.nextLine().trim();

            if (ligne.equals("0")) {
                dos.writeInt(0);
                dos.flush();
                System.out.println("Fin de la communication.");
                break;
            }

            String[] parties = ligne.split("\\s+");

            if (parties.length != 3) {
                System.out.println(" Syntaxe invalide. Format attendu : <nombre> <opérateur> <nombre>");
                continue;
            }

            int op1, op2;
            char operateur;

            try {
                op1 = Integer.parseInt(parties[0]);
            } catch (NumberFormatException e) {
                System.out.println(" Premier opérande invalide : " + parties[0]);
                continue;
            }

            if (parties[1].length() != 1 || "+-*/".indexOf(parties[1].charAt(0)) == -1) {
                System.out.println(" Opérateur invalide : " + parties[1]);
                System.out.println("   Opérateurs acceptés : + - * /");
                continue;
            }
            operateur = parties[1].charAt(0);

            try {
                op2 = Integer.parseInt(parties[2]);
            } catch (NumberFormatException e) {
                System.out.println(" Deuxième opérande invalide : " + parties[2]);
                continue;
            }

            if (operateur == '/' && op2 == 0) {
                System.out.println(" Division par zéro impossible.");
                continue;
            }

            dos.writeInt(op1);
            dos.writeChar(operateur);
            dos.writeInt(op2);
            dos.flush();

            int resultat = dis.readInt();

            if (resultat == Integer.MIN_VALUE) {
                System.out.println("️ Erreur renvoyée par le serveur.");
            } else {
                System.out.println("Résultat : " + op1 + " " + operateur + " " + op2 + " = " + resultat);
            }
        }

        dis.close();
        dos.close();
        socket.close();
        scanner.close();
    }
}