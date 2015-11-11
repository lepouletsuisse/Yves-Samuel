/*
 -----------------------------------------------------------------------------------
 Laboratoire : 04
 Fichier     : Compte.java
 Auteur(s)   : Samuel Darcey & Yves AthanasiadÃ¨s
 Date        : 08.11.2015

 But         : <Ã  complÃ©ter>

 Remarque(s) : <Ã  complÃ©ter>

 Compilateur : jdk1.8.0_60
 -----------------------------------------------------------------------------------
*/
package Compte;

/**
 *
 */
public class Compte {
   /**
    *
    */
   private final static double DEFAULT_SOLDE = 0.0;

   /**
    *
    */
   private static double defaultRetraitMax = 1000.0;

   /**
    *
    */
   private static double defaultDecouvertMax = 800.0;

   /**
    *
    */
   private static int currentId;

   /**
    *
    */
   private final int ID;

   /**
    *
    */
   private final String TITULAIRE;

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
    * CrÃ©er une instance de la classe Compte avec les valeurs par dÃ©faut, exceptÃ© le titulaire
    * @param nom  Le nom du titulaire du compte
    * 
    */
   public Compte(String nom) throws IllegalArgumentException {
      this(nom, DEFAULT_SOLDE, defaultDecouvertMax, defaultRetraitMax);
   }

   /**
    *
    */
   public Compte(String nom, double depot) throws IllegalArgumentException {
      this(nom, depot, defaultDecouvertMax, defaultRetraitMax);
   }

   /**
    * CrÃ©er une instance de la classe Compte avec les valeurs par dÃ©faut, exceptÃ© le titulaire et le solde
    * @param nom
    * @param depot
    * @throws IllegalArgumentException
    */
   public Compte(String nom, double depot, double decouvert) throws IllegalArgumentException {
      this(nom, depot, decouvert, defaultRetraitMax);
   }

   /**
    *
    */
   public Compte(String nom, double depot, double decouvert, double retrait) throws IllegalArgumentException {
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
    * @return
    */
   public String toString() {
      return "\nId: " + ID + "\nTitulaire: " + TITULAIRE + "\nDecouvert Max: "
              + decouvertMax + "\nRetrait Max: " + retraitMax + "\nSolde: "
              + solde + "\nDecouvert?: " + (estDecouvert() ? "Oui" : "Non") + "\n";
   }

   /**
    * @return
    */
   public int getId() {
      return ID;
   }

   /**
    * @return
    */
   public String getTitulaire() {
      return TITULAIRE;
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

   /**
    *
    */
   public static double getDefaultSolde() {
      return DEFAULT_SOLDE;
   }

   /**
    *
    */
   public static double getDefaultDecouvertMax() {
      return defaultDecouvertMax;
   }

   /**
    *
    */
   public static double getDefaultRetraitMax() {
      return defaultRetraitMax;
   }

   /**
    * @param montant
    * @return
    */
   public void setDecouvertMax(double montant) throws IllegalArgumentException{
      if (montant < 0 || solde >= -montant) {
         throw new IllegalArgumentException();
      }
      decouvertMax = montant;
   }

   /**
    * @param montant
    * @return
    */
   public void setRetraitMax(double montant) throws IllegalArgumentException{
      if (montant < 0) {
         throw new IllegalArgumentException();
      }
      retraitMax = montant;
   }

   /**
    *
    */
   public static void setDefaultDecouvertMax(double montant)throws IllegalArgumentException{
      if(montant < 0){
         throw new IllegalArgumentException();
      }
      defaultDecouvertMax = montant;
   }

   /**
    *
    */
   public static void setDefaultRetraitMax(double montant) throws IllegalArgumentException{
      if(montant < 0){
         throw new IllegalArgumentException();
      }
      defaultRetraitMax = montant;
   }

   /**
    * @return
    */
   public boolean estDecouvert() {
      return decouvert > 0;
   }

   /**
    *
    */
   public double debitMaxAutorise() {
      //Vérifie si on peut retirer le montant maximum ou non
      if (Math.abs(solde - retraitMax) > decouvertMax) {
         return solde + decouvertMax;
      }
      return retraitMax;
   }

   /**
    * @param montant
    * @return
    */
   public void crediter(double montant) throws IllegalArgumentException{
      if (montant < 0) {
         throw new IllegalArgumentException();
      }
      solde += montant;
      actualiserDecouvert();
   }

   /**
    * @param montant
    * @return
    */
   public double debiter(double montant) throws IllegalArgumentException{
      if (montant < 0) {
         throw new IllegalArgumentException();
      }
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
    * @param debit
    * @param credit
    * @return
    */
   public static double virer(Compte debit, Compte credit, double montant) throws IllegalArgumentException{
      //Le test du montant est fait dans debiter() et crediter()
      if(debit == null || credit == null){
         throw new IllegalArgumentException();
      }
      montant = debit.debiter(montant);
      credit.crediter(montant);
      return montant;
   }

   /**
    *
    */
   private void actualiserDecouvert() {
      if (solde < 0) {
         decouvert = -solde;
      } else {
         decouvert = 0;
      }
   }

}
