package fr.cda.projet;

import fr.cda.ihm.Formulaire;
import fr.cda.ihm.FormulaireInt;
import fr.cda.util.Site;

import javax.swing.*;
import javax.swing.plaf.OptionPaneUI;

/**
 * Classe GUIModifierQuantite
 */
public class GUIModifierQuantite implements FormulaireInt {
    private GUISite panelPP;
    private Site site;
    private Produit produit;

    /**
     *
     * @param panelPP
     * @param site
     * @param produit
     */
    public GUIModifierQuantite(GUISite panelPP, Site site, Produit produit) {
        this.panelPP = panelPP;
        this.site = site;
        this.produit = produit;

        // Je créé un nouveau formulaire pour modifier la quantité en stock
        Formulaire form = new Formulaire("Modifier la quantité", this, 700, 300);
        form.addZoneText("PRODUIT", "", false, produit.toString(), 600, 200);
        form.addLabel("");
        form.addText("NOUV_QUANTITE", "Nouvelle quantité : ", true, "");
        form.addButton("MODIF_QUANTITE", "Modifier");

        form.afficher();
    }

    /**
     *
     * @param form Le formulaire dans lequel se trouve le bouton
     * @param nom Le nom du bouton qui a ete utilise.
     */
    @Override
    public void submit(Formulaire form, String nom) {
        this.produit.setQuantite(Integer.parseInt(form.getValeurChamp("NOUV_QUANTITE")));
        JOptionPane.showMessageDialog(form.getPanel(),"Modification enregistrée");
        form.fermer();
    }
}
