/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.tests;

import com.esprit.entities.Cours;
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
        sp2.ajouter(new Cours("55dd9", "jad alik","Haythem"));
    }
}
