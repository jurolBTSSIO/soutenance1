// Projet 1 CDA
// 
// NOM,Prenom
// NOM,Prenom
//
package fr.cda.projet;

import fr.cda.util.*;

import java.io.IOException;

// Classe principale d'execution du projet
//
public class Projet
{
    public static void main(String a_args[]) throws IOException, NomFichierIncorrectException {
        Terminal.ecrireStringln("Execution du projet ");
        // creation d'un nouveau site
        try {
            Site site = new Site();
            GUISite ihm = new GUISite(site);
        } catch (NomFichierIncorrectException e) {
            throw new RuntimeException(e);
        }
    }
}
