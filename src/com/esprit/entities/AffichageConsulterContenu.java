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
public class AffichageConsulterContenu {
    private String Titre;
    private String Type;
    private int duree;
    private Button Supp;

    public AffichageConsulterContenu(String Titre, String Type, int duree, Button Supp) {
        this.Titre = Titre;
        this.Type = Type;
        this.duree = duree;
        this.Supp = Supp;
    }

    

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    
    
    public Button getSupp() {
        return Supp;
    }

    public void setSupp(Button Supp) {
        this.Supp = Supp;
    }

    
    
    
    
}
