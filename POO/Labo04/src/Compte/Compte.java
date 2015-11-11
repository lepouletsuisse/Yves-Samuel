/*
 -----------------------------------------------------------------------------------
 Laboratoire : 04
 Fichier     : Compte.java
 Auteur(s)   : Samuel Darcey & Yves Athanasiadès
 Date        : 08.11.2015

 But         : Classe permettant de gérer un compte banquaire
               et de faire des manipulations dessus.

 Remarque(s) : <à compléter>

 Compilateur : jdk1.8.0_60
 -----------------------------------------------------------------------------------
*/

package Compte;

/**
 * Classe permettant la gestion d'un compte banquaire.
 *
 * @author Samuel Darcey, Yves Athanasiadès
 */
public class Compte {
   /**
    * Permet de définir avec quel solde par défaut le compte sera ouvert.
    */
   private final static double DEFAULT_SOLDE = 0.0;

   /**
    * Permet de définir avec quel retrait maximum le compte sera paramétré.
    */
   private static double defaultRetraitMax = 1000.0;

   /**
    * Permet de définir avec quel seuil de découvert le compte sera paramétré.
    */
   private static double defaultDecouvertMax = 800.0;

   /**
    * Défini l'id du prochain compte créé.
    */
   private static int currentId;

   /**
    * L'id utilisé par le compte.
    */
   private final int ID;

   /**
    * Le nom du titulaire du compte.
    */
   private final String TITULAIRE;

   /**
    * Le solde actuel du compte.
    */
   private double solde;

   /**
    * Le découvert actuel du compte (en valeur absolue).
    */
   private double decouvert;

   /**
    * Le découvert maximum autorisé sur ce compte.
    */
   private double decouvertMax;

   /**
    * Le retrait maximum autorisé sur ce compte.
    */
   private double retraitMax;

   /**
    * Crée une instance de la classe Compte.
    *
    * @param nom Le nom du titulaire du compte
    * @throws IllegalArgumentException
    */
   public Compte(String nom) throws IllegalArgumentException {
      this(nom, DEFAULT_SOLDE, defaultDecouvertMax, defaultRetraitMax);
   }

   /**
    * Crée une instance de la classe Compte.
    *
    * @param nom   Le nom du titulaire du compte
    * @param depot Le dépot initial du compte
    * @throws IllegalArgumentException
    */
   public Compte(String nom, double depot) throws IllegalArgumentException {
      this(nom, depot, defaultDecouvertMax, defaultRetraitMax);
   }

   /**
    * Crée une instance de la classe Compte.
    *
    * @param nom       Le nom du titulaire du compte
    * @param depot     Le dépot initial du compte
    * @param decouvert Le découvert maximum autorisé sur le compte
    * @throws IllegalArgumentException
    */
   public Compte(String nom, double depot, double decouvert)
           throws IllegalArgumentException {
      this(nom, depot, decouvert, defaultRetraitMax);
   }

   /**
    * Crée une instance de la classe Compte.
    *
    * @param nom       Le nom du titulaire du compte
    * @param depot     Le dépot initial du compte
    * @param decouvert Le découvert maximum autorisé sur le compte
    * @param retrait   Le retrait maximum autorisé sur le compte
    * @throws IllegalArgumentException
    */
   public Compte(String nom, double depot, double decouvert, double retrait)
           throws IllegalArgumentException {
      //Vérifie que les entrées utilisateurs soient bonnes
      if (nom == null || -depot > decouvert || decouvert < 0 || retrait < 0) {
         throw new IllegalArgumentException();
      }
      TITULAIRE = nom;
      ID = currentId++;
      solde = depot;
      decouvertMax = decouvert;
      retraitMax = retrait;
      actualiserDecouvert();
   }

   /**
    * Convertit le Compte en format affichable.
    *
    * @return Une chaine de caractère contenant le compte
    * sous format affichable.
    */
   public String toString() {
      return "\nId: " + ID + "\nTitulaire: " + TITULAIRE + "\nDecouvert Max: "
              + decouvertMax + "\nRetrait Max: " + retraitMax + "\nSolde: "
              + solde + "\nDecouvert?: " + (estDecouvert() ? "Oui" : "Non") + "\n";
   }

   /**
    * Obtient l'id du compte courrant.
    *
    * @return L'id du compte
    */
   public int getId() {
      return ID;
   }

   /**
    * Obtient le titulaire du compte.
    *
    * @return Le titulaire du compte
    */
   public String getTitulaire() {
      return TITULAIRE;
   }

   /**
    * Obtient le solde du compte.
    *
    * @return Le solde actuel du compte
    */
   public double getSolde() {
      return solde;
   }

   /**
    * Obtient le découvert actuel du compte.
    *
    * @return Le découvert actuel du compte
    */
   public double getDecouvert() {
      return decouvert;
   }

   /**
    * Obtient le découvert maximum autorisé sur le compte.
    *
    * @return Le découvert maximum du compte
    */
   public double getDecouvertMax() {
      return decouvertMax;
   }

   /**
    * Obtient le retrait maximum autorisé sur le compte.
    *
    * @return Le retrait maximum
    */
   public double getRetraitMax() {
      return retraitMax;
   }

   /**
    * Obtient le solde par défaut sur un compte.
    *
    * @return Le solde par défaut
    */
   public static double getDefaultSolde() {
      return DEFAULT_SOLDE;
   }

   /**
    * Obtient le découvert maximum autorisé par défaut sur un compte.
    *
    * @return Le découvert maximum par défaut
    */
   public static double getDefaultDecouvertMax() {
      return defaultDecouvertMax;
   }

   /**
    * Obtient le retrait maximum autorisé par défaut sur un compte.
    *
    * @return Le retrait maximum par défaut
    */
   public static double getDefaultRetraitMax() {
      return defaultRetraitMax;
   }

   /**
    * Permet de définir le découvert maximum autorisé sur le compte.
    *
    * @param montant Le montant du découvert maximum.
    * @throws IllegalArgumentException
    */
   public void setDecouvertMax(double montant) throws IllegalArgumentException {
      //Vérifie que le montant est correct
      if (montant < 0 || solde < -montant) {
         throw new IllegalArgumentException();
      }
      decouvertMax = montant;
   }

   /**
    * Permet de définir le retrait maximum autorisé sur le compte.
    *
    * @param montant Le montant du retrait maximum
    * @throws IllegalArgumentException
    */
   public void setRetraitMax(double montant) throws IllegalArgumentException {
      //Vérifie que le montant est correct
      if (montant < 0) {
         throw new IllegalArgumentException();
      }
      retraitMax = montant;
   }

   /**
    * Permet de définir le découvert maximum autorisé par défaut sur un compte.
    *
    * @param montant Le montant du découvert maximum
    * @throws IllegalArgumentException
    */
   public static void setDefaultDecouvertMax(double montant)
           throws IllegalArgumentException {
      //Vérifie que le montant est correct
      if (montant < 0) {
         throw new IllegalArgumentException();
      }
      defaultDecouvertMax = montant;
   }

   /**
    * Permet de définir le retrait maximum autorisé par défaut sur un compte.
    *
    * @param montant Le montant du retrait maximum
    * @throws IllegalArgumentException
    */
   public static void setDefaultRetraitMax(double montant)
           throws IllegalArgumentException {
      //Vérifie que le montant est correct
      if (montant < 0) {
         throw new IllegalArgumentException();
      }
      defaultRetraitMax = montant;
   }

   /**
    * Vérifie si le comtpe est à découvert
    *
    * @return Si le compte est a découvert ou pas
    */
   public boolean estDecouvert() {
      return decouvert > 0;
   }

   /**
    * Vérifie quel est le retrait maximum pouvant être effectué sur le compte.
    *
    * @return Le montant du retrait maximum
    */
   public double debitMaxAutorise() {
      //Vérifie si on peut retirer le montant maximum ou non
      if (Math.abs(solde - retraitMax) > decouvertMax) {
         return solde + decouvertMax;
      }
      return retraitMax;
   }

   /**
    * Crédite sur le compte une somme donnée.
    *
    * @param montant Le montant à créditer
    * @throws IllegalArgumentException
    */
   public void crediter(double montant) throws IllegalArgumentException {
      //Vérifie que le montant est correct
      if (montant < 0) {
         throw new IllegalArgumentException();
      }
      solde += montant;
      actualiserDecouvert();
   }

   /**
    * Débite sur le compte une somme donnée.
    *
    * @param montant Le montant à débiter
    * @return Le montant débité
    * @throws IllegalArgumentException
    */
   public double debiter(double montant) throws IllegalArgumentException {
      //Vérifie que le montant est correct
      if (montant < 0) {
         throw new IllegalArgumentException();
      }
      //Vérifie que le montant n'excède pas le retrait max
      if (montant > retraitMax) {
         montant = retraitMax;
      }
      //Vérifie que le montant demandé ne dépasse pas le découvert max
      if (Math.abs(solde - montant) > decouvertMax) {
         montant = solde + decouvertMax;
      }
      solde -= montant;
      actualiserDecouvert();
      return montant;
   }

   /**
    * Effectue un virement d'un compte à un autre.
    *
    * @param debit   Le compte à débiter
    * @param credit  Le compte à créditer
    * @param montant Le montant à débiter/créditer
    * @return Le montant débité/crédité
    * @throws IllegalArgumentException
    */
   public static double virer(Compte debit, Compte credit, double montant)
           throws IllegalArgumentException {
      //Vérifie que les comptes existent
      //Le test du montant est fait dans debiter() et crediter()
      if (debit == null || credit == null) {
         throw new IllegalArgumentException();
      }
      montant = debit.debiter(montant);
      credit.crediter(montant);
      return montant;
   }

   /**
    * Met le découvert à jour en fonction du solde du compte.
    */
   private void actualiserDecouvert() {
      if (solde < 0) {
         decouvert = -solde;
      }
      else {
         decouvert = 0;
      }
   }
}
