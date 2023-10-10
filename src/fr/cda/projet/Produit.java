package fr.cda.projet;

import java.util.*;

// Classe de definition d'un produit du stock
//

/**
 * Classe Produit
 */
public class Produit
{
    // Les caracteristiques d'un Produit
    //
    private String  reference;      // reference du produit
    private String  nom;            // nom du produit
    private double  prix;           // prix du produit
    private int     quantite;       // quantité du produit

    // Constructeur

    /**
     *
     * @param reference
     * @param nom
     * @param prix
     * @param quantite
     */
    public Produit(String reference,
                   String nom,
                   double prix,
                   int quantite)
    {
        this.reference = reference;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }
    // Getters et Setters

    /**
     *
     * @return
     */
    public String getReference() {
        return reference;
    }

    /**
     *
     * @param reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return
     */
    public double getPrix() {
        return prix;
    }

    /**
     *
     * @param prix
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     *
     * @return
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     *
     * @param quantite
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     *
     * @return
     */
    public String toString()
    {

        return String.format("%-15s %-50s %3.2f   %3d",reference,nom,prix,quantite);
    }

    /**
     *
     * @return
     */
    public String _toString() {
        return "" + reference + ";" + nom + ";" + prix + ";" + quantite;
    }
}