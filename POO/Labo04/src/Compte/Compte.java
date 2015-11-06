package Compte;/*
 -----------------------------------------------------------------------------------
 Laboratoire : 04
 Fichier     : Compte.java
 Auteur(s)   : Samuel Darcey & Yves Athanasiad�s
 Date        : 06.11.2015

 But         : <� compl�ter>

 Remarque(s) : <� compl�ter>

 Compilateur : jdk1.8.0_60
 -----------------------------------------------------------------------------------
*/

public class Compte {
   private int id;
   private String titulaire;
   private double solde;
   private double decouvert;
   private double decouvertMax;
   private double retraitMax;

   //Constructeur
   public Compte(String titulaire){
      this.titulaire = titulaire;
   }

   //get
   public int getId(){
      return id;
   }

   public String getTitulaire(){
      return titulaire;
   }

   public double getSolde(){
      return solde;
   }

   public double getDecouvert(){
      return decouvert;
   }

   public double getDecouvertMax(){
      return decouvertMax;
   }

   public double getRetraitMax(){
      return retraitMax;
   }

   //Set

   public void crediter(double montant){
      solde += montant;
   }

   public void debiter(double montant){
      solde -= montant;
   }

   public void virer(Compte credit, Compte debit){

   }
}
