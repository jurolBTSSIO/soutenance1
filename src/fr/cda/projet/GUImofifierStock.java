package fr.cda.projet;

import fr.cda.ihm.Formulaire;
import fr.cda.ihm.FormulaireInt;
import fr.cda.util.CommandeNullException;
import fr.cda.util.Site;

import javax.swing.*;

/**
 * Classe GUIModifierStock
 */
public class GUImofifierStock implements FormulaireInt {
    private GUISite panelPP;
    private Site site;

    /**
     * Constructeur
     * @param panelPP
     * @param site
     */
    public GUImofifierStock(GUISite panelPP, Site site) {
        this.panelPP = panelPP;
        this.site = site;
        // Je créé un tableau à partir de l'arraylist
        String[] produits = new String[this.site.getStock().size()];

        for (int i = 0; i < this.site.getStock().size(); i++) {
            produits[i] = this.site.getStock().get(i).toString();
        }

        // Jé créé un nouveau formulaire
        Formulaire form = new Formulaire("Mofifier le stock", this, 800, 400);
        form.addListScroll("STOCK", "Liste des produits", true, produits, 650, 300);
        form.addLabel("");
        form.addLabel("");
        form.addButton("CHOISIR", "Choisir");
        form.afficher();
    }

    /**
     *
     * @return
     */
    public GUISite getPanelPP() {
        return panelPP;
    }

    /**
     *
     * @param panelPP
     */
    public void setPanelPP(GUISite panelPP) {
        this.panelPP = panelPP;
    }

    /**
     *
     * @return
     */
    public Site getSite() {
        return site;
    }

    /**
     *
     * @param site
     */
    public void setSite(Site site) {
        this.site = site;
    }

    /**
     *
     * @param form Le formulaire dans lequel se trouve le bouton
     * @param nom Le nom du bouton qui a �t� utilis�.
     */
    @Override
    public void submit(Formulaire form, String nom) {
        // Je créé un nouveau formulaire et la méthode modifierStock
        // Retourne le produit choisi que je passe en paramètre
        try {
            new GUIModifierQuantitetock(panelPP, this.site, site.modifierStock(form));
            form.fermer();
        } catch (CommandeNullException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
