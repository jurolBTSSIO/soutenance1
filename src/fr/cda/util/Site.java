package fr.cda.util;

import java.util.*;

import fr.cda.ihm.Formulaire;
import fr.cda.projet.Commande;
import fr.cda.projet.Produit;

import javax.swing.*;

/**
 * Classe Site
 */
public class Site {
    private ArrayList<Produit> stock;       // Les produits du stock
    private ArrayList<Commande> commandes;  // Les bons de commande

    /**
     * Constructeur
     */
    public Site() throws NomFichierIncorrectException {
        this.stock = new ArrayList<Produit>();
        this.commandes = new ArrayList<Commande>();

        // lecture du fichier data/Produits.txt
        // et data/Commandes.txt
        //  pour chaque ligne on cree un Produit que l'on ajoute a stock
        // On gere une exception sur le nom du fichier
        try {
            initialiserStock("data/Produits.txt");
            initialiserCommandes("data/Commandes.txt");
        } catch (NomFichierIncorrectException e) {
            e.fillInStackTrace();
        }
    }
    /**
     * Chargement du fichier de stock
     *
     * @param nomFichier
     */
    private void initialiserStock(String nomFichier) throws NomFichierIncorrectException {

        if (nomFichier == null || nomFichier.isEmpty()) {
            throw new NomFichierIncorrectException("Le nom de fichier n'est pas correct.");
        }

        String[] lignes = Terminal.lireFichierTexte(nomFichier);

        // On boucle sur les ligne du fichier Produit.txt
        for (String ligne : lignes) {
            System.out.println(ligne);
            String[] champs = ligne.split("[;]", 4);
            String reference = champs[0];
            String nom = champs[1];
            double prix = Double.parseDouble(champs[2]);
            int quantite = Integer.parseInt(champs[3]);
            Produit p = new Produit(reference,
                    nom,
                    prix,
                    quantite
            );
            stock.add(p);
        }
    }
    /**
     * Chargement du fichier Commandes.txt
     *
     * @param nomFichier
     */
    private void initialiserCommandes(String nomFichier) throws NomFichierIncorrectException {
        // Je teste si le fichier existe
        if (nomFichier == null || nomFichier.isEmpty()) {
            throw new NomFichierIncorrectException("Le nom de fichier n'est pas correct.");
        }
        // Je lis le fichier
        String[] lignes = Terminal.lireFichierTexte(nomFichier);
        // Je créé le tableau de références
        ArrayList<String> references;
        Commande commande = null;

        // On boucle sur les lignes du fichier Commandes.txt
        try {
            for (String ligne : lignes) {
                // Je split le fichier par le ";"
                String[] champs = ligne.split(";", 5);
                // J'initialise chaque varialble
                int numero = Integer.parseInt(champs[0]);
                String date = champs[1];
                String client = champs[2];
                boolean isLivre = Boolean.parseBoolean(champs[3]);
                references = new ArrayList<>();
                references.add(champs[4]);

                // On teste si la commande existe, si elle existe on ajoute une reference,
                // sinon on cree une nouvelle commande
                if (!isCommandeExist(numero)) {
                    commande = new Commande(numero, date, client, isLivre, references);
                    this.commandes.add(commande);
                } else {
                    commande.getReferences().add(champs[4]);
                }
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Teste si une commande existe
     *
     * @param numero
     * @return
     */
    public boolean isCommandeExist(int numero) {
        // Je cree le booleen
        boolean isExist = false;

        // Je boucle sur les commandes
        for (Commande commande : this.commandes) {

            // Si le numero correspond je passe a true le booleen
            if (commande.getNumero() == numero) {
                isExist = true;
            } else {
                isExist = false;
            }
        }
        return isExist;
    }

    /**
     * Methode qui retourne sous la forme d'une chaine de caractere
     * tous les produits du stock
     *
     * @return
     */
    public String listerTousProduits() {
        StringBuilder res = new StringBuilder();
        // Je boucle sur le stock
        for (Produit prod : this.stock)
            res.append(prod.toString()).append("\n");

        return res.toString();
    }

    /**
     * Méthode qui liste tous les produits
     * pour la sauvegarde
     *
     * @return
     */
    public String listerTousProduitsSauvegarde() {
        StringBuilder res = new StringBuilder();

        for (Produit prod : this.stock)
            res.append(prod.toStringSauvegarde()).append("\n");
        return res.toString();
    }

    /**
     * Methode qui retourne sous la forme d'une chaine de caractere  toutes les commandes
     *
     * @return
     */
    public String listerToutesCommandes() {
        // Je cree un stringbuilder
        StringBuilder resultat = new StringBuilder();
        // Je boucle sur les commandes
        for (Commande commande : this.commandes) {
            resultat.append(commande.toString()).append("\n");
            resultat.append("===================================================================== \n");
        }
        return resultat.toString();
    }

    /**
     * Deuxième méthode pour lister les commandes et les sauvegarder
     *
     * @return
     */
    public String listerToutesCommandesSauvegarde() {
        // Je cree un stringbuilder
        StringBuilder resultat = new StringBuilder();
        // Je boucle sur les commandes
        for (Commande commande : this.commandes) {

            // Je boucle sur les references
            for (String reference : commande.getReferences()) {
                resultat.append(commande._toString()).append(reference).append("\n");
            }
        }
        return resultat.toString();
    }

    /**
     * Methode qui retourne sous la forme d'une chaine de caractere une commande
     *
     * @param numero
     * @return
     */
    public String listerCommande(int numero) {
        String res = "";

        // Je boucle sur les commandes
        for (Commande commande : this.commandes) {

            // Et si le numero passe en parametre est egal au numero de la commande
            // Je le concatene a la string
            if (commande.getNumero() == numero) {
                res = res + commande;
                break;
            }
        }
        // Si le numero de commande n'existe pas
        if (res == "") {
            JOptionPane.showMessageDialog(null, "La commande n'éxiste pas!");
        }
        return res;
    }

    /**
     * Methode qui retourne une commande par son numéro
     *
     * @param numero
     * @return
     */
    public Commande retounerCommande(int numero) throws CommandeNullException {
        Commande commandeChoisie = null;
        for (Commande commande : this.commandes) {

            if (commande.getNumero() == numero) {
                commandeChoisie = commande;
                break;
            }
        }
        if (commandeChoisie == null) {
            throw new CommandeNullException("La commande n'existe pas!");
        }
        if (commandeChoisie.isLivre()) {
            throw new CommandeNullException("La commande est deja livrée!");
        }
        return commandeChoisie;
    }
    /**
     * Methode qui livre la commande
     *
     * @return
     */
    public String livrerCommande() {
        // Je créé le string que je vais retourner
        StringBuilder retour = new StringBuilder();
        retour.append("Commande non livrees : \n");
        retour.append("==================================================== \n");

        // Je boucle sur la liste de commandes
        for (Commande commande : this.commandes) {
            String raisons = "";

            // Je teste si la commade a déjà été livrée
            if (!commande.isLivre()) {

                // Je boucle sur la liste de références
                for (String reference : commande.getReferences()) {
                    String[] parts = reference.split("=");
                    String referenceProduit = parts[0];
                    // Je récupére la quantité
                    int quantiteDemandee = Integer.parseInt(parts[1].trim());
                    // On appelle la methode pour trouver le produit
                    Produit produit = trouverProduit(referenceProduit);

                    // Je compare la quantité avec celle du stock
                    // Si elle est supérieur ou egale je fais la soustraction
                    // Et je set à true isLivre
                    if (produit != null && produit.getQuantite() >= quantiteDemandee) {
                        produit.setQuantite(produit.getQuantite() - quantiteDemandee);
                        commande.setIsLivre(true);
                        // Sinon je cree une raison et je set a false isLivre
                    } else {
                        commande.setIsLivre(false);
                        raisons += "       Il manque " + (Math.abs(produit.getQuantite() - quantiteDemandee)) + " " + produit.getReference() + " pour la commande " + commande.getNumero() + "\n";
                    }
                    commande.setRaisons(raisons);
                }

                // Si la commande n'est pas valide j'implemente le Stringbuilder
                if (!commande.isLivre()) {
                    retour.append(commande).append(commande.getRaisons()).append("===================================================== \n");
                }
            }
        }
        return retour.toString();
    }

    /**
     * Méthode qui affiche la somme totale
     *
     * @return
     */
    public String afficherSomme() {
        // Je cree un stringbuider pour le retour
        StringBuilder retour = new StringBuilder();
        // J'initialise la variable de la somme totale
        double somme = 0;
        // Je boucle sur les commandes
        for (Commande commande : this.commandes) {

            // Je teste si la commande est livree
            if (commande.isLivre()) {
                retour.append("Commande : ").append(commande.getNumero()).append("\n");

                // Je boucle sur les references
                for (String reference : commande.getReferences()) {
                    // Je split pour recuperer la quantite
                    String[] champs = reference.split("=", 2);
                    int quantite = Integer.parseInt(champs[1].trim());
                    System.out.println(champs[1] + "rere");

                    // Je boucle sur les produit
                    for (Produit produit : this.stock) {

                        // Je teste le nom du produit
                        // Si c'est le meme je multiplie la quantite par son prix
                        if (produit.getReference().equalsIgnoreCase(champs[0].trim())) {
                            somme += produit.getPrix() * quantite;
                            retour.append("                   ").append(produit.getNom()).append(" ").append(champs[1]).append(" : ");
                            retour.append((double) Math.round(produit.getPrix() * Integer.parseInt(champs[1]) * 100) / 100).append(" euros \n");
                        }
                    }
                }
            }
        }
        return retour.append("========================================= \n").append("SOMME : ").append((double) Math.round(somme * 100) / 100).append(" euros\n").toString();
    }

    /**
     * Methode qui permet trouver un produit
     *
     * @param reference
     * @return
     */
    public Produit trouverProduit(String reference) {
        Produit produitTrouve = null;

        for (Produit produit : this.stock) {
            // Print debug information
            System.out.println("Comparing: " + produit.getReference() + " with " + reference);

            if (produit.getReference().equalsIgnoreCase(reference.trim())) {
                produitTrouve = produit;
                break;
            }
        }
        return produitTrouve;
    }

    /**
     * Je créé une méthode pour sauvegarder
     */
    public void sauvegarder() {
        StringBuffer stringBuffer1 = new StringBuffer(this.listerTousProduitsSauvegarde());
        StringBuffer stringBuffer2 = new StringBuffer(this.listerToutesCommandesSauvegarde());
        Terminal.ecrireFichier("data/Commandes.txt", stringBuffer2);
        Terminal.ecrireFichier("data/Produits.txt", stringBuffer1);
    }

    /**
     * Méthode qui permet de modifier les quantites en stock
     * @param form
     * @return
     */
    public Produit modifierStock(Formulaire form) {
        Produit produitChoisi = null;

        // Je cherhce si le produit est en stock
        for (Produit produit : this.stock) {

            // je teste si la valeur du champ se rapporte à un produit
            if (form.getValeurChamp("STOCK").equalsIgnoreCase(produit.toString())) {
                produitChoisi = produit;
                break;
            }
        }
        return produitChoisi;
    }

    /**
     * Méthode qui ajoute une reference à une arrayList de references
     * @param reference
     * @return
     */
    public ArrayList<String> ajouterReference(String reference) {
        ArrayList<String> nouvellesReferences = new ArrayList<>();
        nouvellesReferences.add(reference);
        return nouvellesReferences;
    }

    //----------------------------------- SETTERS ET GETTERS----------------------------------------------------->
    public ArrayList<Produit> getStock() {
        return stock;
    }
    public void setStock(ArrayList<Produit> stock) {
        this.stock = stock;
    }
    public ArrayList<Commande> getCommandes() {
        return commandes;
    }
    public void setCommandes(ArrayList<Commande> commandes) {
        this.commandes = commandes;
    }
}