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
        
        ServiceCours sp1 = new ServiceCours();
        ServiceChapitre sp2 = new ServiceChapitre();
        ServiceContenu sp3 = new ServiceContenu();
        ServiceProgres sp4 = new ServiceProgres();
        
        // ---------------------- COURS ------------------------
        
        //sp1.ajouter(new Cours("55dd", "Introduction au web","55","web"));
        //sp1.modifier(new Cours("55dd9", "jad alik","gtt","web"));
        //sp1.supprimer(new Cours("55dd9"));
        //System.out.println(sp1.afficher());
        
        // --------------------- Chapitre ----------------------
        
        
        //sp2.ajouter(new Chapitre("655dd","html","55dd9"));
        //sp2.modifier(new Chapitre("655dd","css","55dd9"));
        //sp2.supprimer(new Chapitre("655dd"));
        //sp2.afficher(new Chapitre("655dd","html","55dd9"));
        
        // ------------------------ CONTENU --------------------
        
        //sp3.ajouter(new Contenu("888","video",5,"www","655dd"));
        //sp3.modifier(new Contenu("888","video",5,"www","655dd"));
        //sp3.supprimer(new Contenu("888"));
        //System.out.println(sp3.afficher());
        
        // ------------------------ Tuteur ---------------------
        
        //sp5.ajouter(new Tuteur("gtt", "ff", "haythem", 85558855, "99999+"));
        //sp5.modifier(new Tuteur("gtt","ff","jasser",1525852,"............"));
        //sp5.supprimer(new Tuteur("gtt"));
        //System.out.println(sp5.afficher());
        
        // ------------------------ Progres --------------------
        
        sp4.ajouter(new Progres("55dd9", "gtt", 50, 0, false));
        //sp4.modifier(new Progres("55dd9", "ff", 100, 15, true));
        //sp4.supprimer(new Progres("55dd9", "ff"));
        //System.out.println(sp4.afficher());
        
        
    }
}
