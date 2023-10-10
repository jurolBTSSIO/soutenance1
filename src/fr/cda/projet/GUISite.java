package fr.cda.projet;

import fr.cda.ihm.*;
import fr.cda.util.CommandeNullException;
import fr.cda.util.Site;
import fr.cda.util.Terminal;

import javax.swing.*;
import java.util.ArrayList;

// Classe de definition de l'IHM principale du compte
//
public class GUISite implements FormulaireInt
{
    private Site site;  // Le site
    private int numeroCommande;
    // Constructeur
    //
    public GUISite(Site site)
    {
        this.site = site;

        // Creation du formulaire
        Formulaire form = new Formulaire("Site de vente",this,1100,730);
        
        //  Creation des elements de l'IHM
        //
        form.addLabel("Afficher tous les produits du stock");
        form.addButton("AFF_STOCK","Tous le stock");
        form.addButton("MOD_STOCK", "Modifier");
        form.addLabel("");
        form.addLabel("Afficher tous les bons de commande");
        form.addButton("AFF_COMMANDES","Toutes les commandes");
        form.addLabel("");
        form.addText("NUM_COMMANDE","Numero de commande",true,"");
        form.addButton("AFF_COMMANDE","Afficher");
        form.addLabel("");
        // j' ajoute un bouton modifier
        form.addButton("MODIFIER", "modifier");
        // J'ajoute un label vide pour créer un espace
        form.addLabel("");
        form.addButton("LIVRER", "Livrer");
        form.addButton("AFFICHER_VENTES", "Calculer les ventes");
        // J'ajoute un label vide pour créer un espace
        form.addLabel("");
        // J'ajoute un bouton de sauvegrarde
        form.addButton("SAUVEGARDER", "Sauvegarder");
        // J'ajoute un label vide pour créer un espace
        form.addLabel("");
        // J'ajoute un bouton pour la fermeture du formulaire
        form.addButton("FERMER", "Fermer");

        form.setPosition(400,0);
        form.addZoneText("RESULTATS","Resultats",
                            true,
                            "",
                            600,700);

        // Affichage du formulaire
        form.afficher();
    }

    // Methode appellee quand on clique dans un bouton
    //
    public void submit(Formulaire form,String nomSubmit)
    {

        // Affichage de tous les produits du stock
        //
        if (nomSubmit.equals("AFF_STOCK"))
            {
                String res = site.listerTousProduits();
                form.setValeurChamp("RESULTATS",res);
            }

        // Modification du stock
        if (nomSubmit.equals("MOD_STOCK")) {
            // Affichge du formulaire de modificatio
            new GUImofifierStock(this, site);
        }
        // Affichage de toutes les commandes
        //
        if (nomSubmit.equals("AFF_COMMANDES"))
            {
                form.setValeurChamp("RESULTATS", this.site.listerToutesCommandes());
            }

        // Affichage d'une commande
        //
        if (nomSubmit.equals("AFF_COMMANDE"))
            {
                String numStr = form.getValeurChamp("NUM_COMMANDE");
                int num = 0;
                try {
                    num = Integer.parseInt(numStr);
                    // Utiliser 'num' ici
                } catch (NumberFormatException e) {
                    // Gérer l'exception, par exemple, afficher un message d'erreur à l'utilisateur
                    JOptionPane.showMessageDialog(form.getPanel(), "Le numéro de commande n'est pas valide.");
                }
                numeroCommande = num;
                String res = site.listerCommande(num);
                form.setValeurChamp("RESULTATS", res);
            }
        // Livraison des commandes
        if (nomSubmit.equals("LIVRER")) {
            form.setValeurChamp("RESULTATS", site.livrerCommande());
        }
        // Modifier les commandes non livrées
        if (nomSubmit.equals("MODIFIER")) {

            // Levee d'exception si la commande est nulle
            try {
                new GUIModifierCommande(this, site, numeroCommande);
            } catch (CommandeNullException e) {
                JOptionPane.showMessageDialog(form.getPanel(), e.getMessage());
            }
        }
        // Afficher la somme totale
        if (nomSubmit.equals("AFFICHER_VENTES")) {
            // J'appelle la méthode afficherSomme
            form.setValeurChamp("RESULTATS", this.site.afficherSomme());
        }
        // Sauvegarde dans les fichiers
        if (nomSubmit.equals("SAUVEGARDER")) {
            site.sauvegarder();
        }
        // Fermeture et sauvegarde du site
        if (nomSubmit.equals("FERMER")) {
            site.sauvegarder();
            form.fermer();
        }
    }

}