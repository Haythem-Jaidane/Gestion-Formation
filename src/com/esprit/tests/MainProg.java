/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.tests;

import com.esprit.entities.*;
import com.esprit.services.*;

/**
 *
 * @author abdel
 */
public class MainProg {
    
    public static void main(String[] args) {

        //sp1.ajouter(new Personne("Fedi", "Ahmed"));
        //sp1.modifier(new Personne(1, "Zaher", "Hamdi"));
        //sp1.supprimer(new Personne(1));
        //System.out.println(sp1.afficher());
        ServiceCours sp2 = new ServiceCours();
        ServiceChapitre sp1 = new ServiceChapitre();
        ServiceContenu sp3 = new ServiceContenu();
        //sp2.ajouter(new Cours("55dd9", "jad alik","Haythem","web"));
        //sp1.ajouter(new Chapitre("655dd","html","55dd9"));
        
        // ------------------------ CONTENU --------------------
        
        //sp3.ajouter(new Contenu("888","video",5,"www","655dd"));
        //sp3.modifier(new Contenu("888","video",5,"www","655dd"));
        sp3.supprimer(new Contenu("888"));
        //System.out.println(sp3.afficher());
        
        
    }
}
