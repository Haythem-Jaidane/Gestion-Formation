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

    public AffichageConsulter(String Titre, String Categorie, Button Supprimer) {
        this.Titre = Titre;
        this.Categorie = Categorie;
        this.Supprimer = Supprimer;
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
    
    
    
}
