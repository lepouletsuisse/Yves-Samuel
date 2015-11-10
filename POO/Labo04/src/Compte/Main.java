/*
 -----------------------------------------------------------------------------------
 Laboratoire : <nn>
 Fichier     : Main.java
 Auteur(s)   : Samuel Darcey
 Date        : 08.11.2015

 But         : <à compléter>

 Remarque(s) : <à compléter>

 Compilateur : jdk1.8.0_60
 -----------------------------------------------------------------------------------
*/

package Compte;

public class Main {
   public static void main(String args[]){

      Compte c1 = new Compte("J. DUPONT", 1000);
      c1.setDecouvertMax(1000);

      Compte c2 = new Compte("C. DURAND");

      System.out.print(c1);
      System.out.print(c2);

      c1.debiter(500);
      System.out.print(c1);

      c2.crediter(500);
      System.out.print(c1);

      for(int i = 0 ; i < 3 ; i++){
         c1.debiter(5000);
         System.out.print(c1);
      }

      c1.crediter(500);
      System.out.print(c1);

      Compte.virer(c1, c2, 500);

      System.out.print(c1);
      System.out.print(c2);

   }
}
