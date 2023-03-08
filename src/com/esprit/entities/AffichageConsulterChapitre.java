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
public class AffichageConsulterChapitre {
    private String Titre;
    private Button Supp;
    private Button Con;

    public AffichageConsulterChapitre(String Titre, Button Supp, Button Con) {
        this.Titre = Titre;
        this.Supp = Supp;
        this.Con = Con;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public Button getSupp() {
        return Supp;
    }

    public void setSupp(Button Supp) {
        this.Supp = Supp;
    }

    public Button getCon() {
        return Con;
    }

    public void setCon(Button Con) {
        this.Con = Con;
    }
    
    
    
}
