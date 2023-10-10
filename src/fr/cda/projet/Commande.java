package fr.cda.projet;

import java.util.*;

// Classe de definition d'une commande
//
public class Commande
{
    // Les caracteristiques d'une commande
    //
    private int     numero;         // numero de la commande
    private String  date;           // date de la commande. Au format JJ/MM/AAAA
    private String  client;         // nom du client
    private ArrayList<String> references; // les references des produits de la commande
    private boolean isLivre = false; // Booleén pour savoir si le produit a été livré
    private String raisons; // Descriptif des raisons pour lesquelles le bon de commande n'a pas pu être livré.

    /**
     *
     * @param numero
     * @param date
     * @param client
     * @param isLivre
     * @param references
     */
    public Commande(int numero, String date, String client, boolean isLivre, ArrayList<String> references) {
        this.numero = numero;
        this.date = date;
        this.client = client;
        this.references = references;
        this.isLivre = isLivre;
    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public ArrayList<String> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<String> references) {
        this.references = references;
    }
    public boolean isLivre() {
        return isLivre;
    }
    public void setIsLivre(boolean livre) {
        this.isLivre = livre;
    }
    public String getRaisons() {
        return raisons;
    }

    public void setRaisons(String raisons) {
        this.raisons = raisons;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return  "Commande : " + numero + "\n\n" +
                "   Date         : " + date + "\n" +
                "   Client       : "        + client + "\n" +
                "   RefProduits  : " + references + "\n" +
                "   Livraison    : " + isLivre + "\n";
    }

    /**
     * Je créer une deuxième méthode pour l'écriture du fichier
     * @return
     */
    public String _toString() {
        return numero + ";" + date + ";" + client +  ";" + isLivre + ";";
    }
}