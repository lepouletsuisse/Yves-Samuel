/*
 -----------------------------------------------------------------------------------
 Laboratoire : 04
 Fichier     : Main.java
 Auteur(s)   : Samuel Darcey & Yves Athanasiadès
 Date        : 08.11.2015

 But         : Fichier de tests, destiné à contrôler le bon fonctionnement
               de la classe Compte.

 Remarque(s) :

 Compilateur : jdk1.8.0_60
 -----------------------------------------------------------------------------------
*/

package Compte;

/**
 * Classe permettant de tester le bon fonctionnement de la classe Compte.
 *
 * @author Samuel Darcey, Yves Athanasiadès
 */
public class Main {
   public static void main(String args[]) {
      testDemande();
      testSupplementaire();
   }

   /**
    * Effectue les tests explicitements demandés
    */
   public static void testDemande() {
      System.out.println("Ceci est la batterie de test demandée sur la fiche de " +
              "laboratoire");

      Compte c1 = new Compte("J. DUPONT", 1000);
      c1.setDecouvertMax(1000);

      Compte c2 = new Compte("C. DURAND");

      System.out.print(c1);
      System.out.print(c2);

      c1.debiter(500);
      System.out.print(c1);

      c1.crediter(500);
      System.out.print(c1);

      for (int i = 0; i < 3; i++) {
         c1.debiter(5000);
         System.out.print(c1);
      }

      c1.crediter(500);
      System.out.print(c1);

      Compte.virer(c1, c2, 500);

      System.out.print(c1);
      System.out.print(c2);
   }

   /**
    * Effectue une série de tests supplémentaires.
    */
   public static void testSupplementaire() {
      System.out.println("\n\nVoici quelques tests supplémentaires qui testent " +
              "des cas plus limites");

      Compte c1 = new Compte("Good User 1", 1000);

      System.out.println("Le compte de " + c1.getTitulaire() + " peut retirer " +
              "actuellement " + c1.debitMaxAutorise() + " car il a un solde de " +
              c1.getSolde() + ", un retrait max de " + c1.getRetraitMax() +
              " et un découvert max de " + c1.getDecouvertMax());

      Compte c3 = new Compte("Good User 3", 0);

      System.out.println("Le compte de " + c3.getTitulaire() + " peut retirer " +
              "actuellement " + c3.debitMaxAutorise() + " car il a un solde de " +
              c3.getSolde() + ", un retrait max de " + c3.getRetraitMax() +
              " et un découvert max de " + c3.getDecouvertMax());

      System.out.println("\nSet decouvertMax et retraitMax à -1...");
      try {
         c1.setDecouvertMax(-1);
         c1.setRetraitMax(-1);
      }
      catch (Exception e) {
         System.out.println("Exception levée! " + e.toString());
      }

      System.out.println("\nCréation d'un compte avec -1000 en solde et 800 de " +
              "découvertMax...");
      try {
         Compte c2 = new Compte("Dummy User 2", -1000);
      }
      catch (Exception e) {
         System.out.println("Exception levée! " + e.toString());
      }

      System.out.println("\nCréation d'un compte avec un nom null...");
      try {
         Compte c2 = new Compte(null);
      }
      catch (Exception e) {
         System.out.println("Exception levée! " + e.toString());
      }

      System.out.println("\nCréation d'un compte avec un decouvertMax négatif...");
      try {
         Compte c2 = new Compte("Dummy User 2", 0, -1000);
      }
      catch (Exception e) {
         System.out.println("Exception levée! " + e.toString());
      }

      System.out.println("\nCréation d'un compte avec un retraitMax négatif...");
      try {
         Compte c2 = new Compte("Dummy User 2", 0, 1000, -1000);
      }
      catch (Exception e) {
         System.out.println("Exception levée! " + e.toString());
      }

      System.out.println("\nTentative de virement sur un compte null...");
      try {
         Compte.virer(c1, null, 1000);
      }
      catch (Exception e) {
         System.out.println("Exception levée! " + e.toString());
      }
   }
}
