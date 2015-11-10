/*
 -----------------------------------------------------------------------------------
 Laboratoire : <nn>
 Fichier     : hgh.java
 Auteur(s)   : Samuel Darcey
 Date        : 08.11.2015

 But         : <à compléter>

 Remarque(s) : <à compléter>

 Compilateur : jdk1.8.0_60
 -----------------------------------------------------------------------------------
*/
package Compte;

/*
* TODO: constructeur, verifier bonne implementation, verifier donner,
* TODO: verifier formatage
* */

/**
 *
 */
public class Compte {

   private final static double DEFAULT_SOLDE = 0.0;
   private static double defaultRetraitMax = 1000.0;
   private static double defaultDecouvertMax = 800.0;


   private static int currentId;

   /**
    *
    */
   private final int id;

   /**
    *
    */
   private final String titulaire;

   /**
    *
    */
   private double solde;

   /**
    *
    */
   private double decouvert;

   /**
    *
    */
   private double decouvertMax;

   /**
    *
    */
   private double retraitMax;

   /**
    * @param nom
    */
   public Compte(String nom) throws IllegalArgumentException {
      this(nom, DEFAULT_SOLDE);
   }

   /**
    * @param nom
    * @param solde
    */
   public Compte(String nom, double solde) throws IllegalArgumentException {
      titulaire = nom;
      id = currentId++;
      setSolde(solde);
      setDecouvertMax(defaultDecouvertMax);
      setRetraitMax(defaultRetraitMax);

      if (decouvertMax < 0 || -solde > decouvertMax || retraitMax < 0 || nom == null) {
         throw new IllegalArgumentException();
      }
   }

   /**
    * @return
    */
   public String toString() {
      return "Id: " + id + "\nTitulaire: " + titulaire + "\nDecouvert Max: " + decouvertMax + "\nDebit Max: " + retraitMax + "\nSolde: " + solde + "\nDecouvert?: "
              + (estDecouvert() ? "Oui" : "Non") + "\nRetrait Max: "
              + retraitMax + "\n";
   }

   /**
    * @return
    */
   public int getId() {
      return id;
   }

   /**
    * @return
    */
   public String getTitulaire() {
      return titulaire;
   }

   /**
    * @return
    */
   public double getSolde() {
      return solde;
   }

   /**
    * @return
    */
   public double getDecouvert() {
      return decouvert;
   }

   /**
    * @return
    */
   public double getDecouvertMax() {
      return decouvertMax;
   }

   /**
    * @return
    */
   public double getRetraitMax() {
      return retraitMax;
   }

   public static double getDefaultSolde() {
      return DEFAULT_SOLDE;
   }

   public static double getDefaultDecouvertMax() {
      return defaultDecouvertMax;
   }
   
   public static double getDefaultRetraitMax() {
      return defaultRetraitMax;
   }

   /**
    * @param montant
    * @return
    */
   public void setDecouvertMax(double montant) {
      if (montant >= 0) {
         decouvertMax = montant;
      }
   }

   /**
    * @param montant
    * @return
    */
   public void setRetraitMax(double montant) {
      if (montant >= 0) {
         retraitMax = montant;
      }
   }

   public static void setDefaultDecouvertMax(double montant) {
      defaultDecouvertMax = montant;
   }

   public static void setDefaultRetraitMax(double montant) {
      defaultRetraitMax = montant;
   }

   /**
    * @return
    */
   public boolean estDecouvert() {
      return decouvert > 0;
   }

   public double debitMaxAutorise() {
      if (Math.abs(solde - retraitMax) > decouvertMax) {
         return retraitMax;
      }
      return solde + decouvertMax;
   }

   /**
    * @param montant
    * @return
    */
   public boolean crediter(double montant) {
      if (montant < 0) {
         return false;
      }
      solde += montant;
      actualiserDecouvert();
      return true;
   }

   /**
    * @param montant
    * @return
    */
   public double debiter(double montant) {
      if (montant < 0) {
         return 0.0d;
      }
      if (montant > retraitMax) {
         montant = retraitMax;
      }
      if (Math.abs(solde - montant) > decouvertMax) {
         montant = solde + decouvertMax;
      }
      solde -= montant;
      actualiserDecouvert();
      return montant;
   }

   /**
    * @param debit
    * @param credit
    * @return
    */
   public static double virer(Compte debit, Compte credit, double montant) {
      montant = debit.debiter(montant);
      credit.crediter(montant);
      return montant;
   }

   private void actualiserDecouvert() {
      if (solde < 0) {
         decouvert = Math.abs(solde);
      } else {
         decouvert = 0;
      }
   }

}
