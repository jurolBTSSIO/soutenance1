package fr.cda.projet;

import fr.cda.ihm.Formulaire;
import fr.cda.ihm.FormulaireInt;
import fr.cda.util.CommandeNullException;
import fr.cda.util.SaisieIncorrectException;
import fr.cda.util.Site;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Classe GUIMofifier
 */
public class GUIModifierCommande implements FormulaireInt {
    private GUISite formPP;
    private Site site;
    private Commande commandeAModifier;

    /**
     *
     * @param formPP
     * @param site
     * @param numeroCommande
     */
    public GUIModifierCommande(GUISite formPP, Site site, int numeroCommande) throws CommandeNullException {
        this.formPP = formPP;
        this.site = site;

        // Je cree un nouveau formulaire
        Formulaire form = new Formulaire("MODIFIER", this, 320, 200);


        try {
            commandeAModifier = this.site.retounerCommande(numeroCommande);

            // je boucle sur les references de la commande
            for (String reference : commandeAModifier.getReferences()) {
                String[] champs = reference.split("=", 2);
                form.addText(reference, "  " + champs[0] + " : ", true, champs[1]);
            }
            form.addLabel("");
            form.addLabel("");
            form.addLabel("");
            form.addButton("VALIDER", "Valider");
            form.afficher();

        } catch (CommandeNullException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public GUISite getFormPP() {
        return formPP;
    }

    public void setFormPP(GUISite formPP) {
        this.formPP = formPP;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Commande getCommandeAModifier() {
        return commandeAModifier;
    }

    public void setCommandeAModifier(Commande commandeAModifier) {
        this.commandeAModifier = commandeAModifier;
    }

    /**
     *
     * @param form Le formulaire dans lequel se trouve le bouton
     * @param nom Le nom du bouton qui a ete utilise.
     */
    @Override
    public void submit(Formulaire form, String nom)  {
        ArrayList<String> nouvellesReferences = new ArrayList<>();

            // Je boucle sur les references et je split pour changer la quantite
            for (String reference : commandeAModifier.getReferences()) {
                String[] champs = reference.split("=", 2);

                // Teste de la saisie des chaps du formulaire
                if (form.getValeurChamp(reference) == null || form.getValeurChamp(reference).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La sasie est incorrect!");
                } else {
                    nouvellesReferences.add(champs[0] + "=" + form.getValeurChamp(reference));
                }
            }
            commandeAModifier.setReferences(nouvellesReferences);
        // Je ferme la fenetre
        form.fermer();
    }
}
