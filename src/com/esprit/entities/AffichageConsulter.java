/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

import javafx.scene.control.Button;

/**
 *
 * @author LENOVO
 */
public class AffichageConsulter {
    
    private String Titre;
    private String Categorie;
    private Button Supprimer;
    private Button voir_chapitre;
    private String id_cours;

    public AffichageConsulter(String Titre, String Categorie, Button Supprimer, Button voir_chapitre, String id_cours) {
        this.Titre = Titre;
        this.Categorie = Categorie;
        this.Supprimer = Supprimer;
        this.voir_chapitre = voir_chapitre;
        this.id_cours = id_cours;
    }

    

    
    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    public Button getSupprimer() {
        return Supprimer;
    }

    public void setSupprimer(Button Supprimer) {
        this.Supprimer = Supprimer;
    }

    public Button getVoir_chapitre() {
        return voir_chapitre;
    }

    public void setVoir_chapitre(Button voir_chapitre) {
        this.voir_chapitre = voir_chapitre;
    }
    
    

    public String getId_cours() {
        return id_cours;
    }

    public void setId_cours(String id_cours) {
        this.id_cours = id_cours;
    }

    
    
    
}
