/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author LENOVO
 */
public class AfficahageMainInterface {
    private String Titre;
    private String Tuteur_nom;
    private String Categorie;
    private int duree;
    private ProgressBar progress;
    private Button B;

    public AfficahageMainInterface(String Titre, String Tuteur_nom, String Categorie, int duree, ProgressBar progress, Button B) {
        this.Titre = Titre;
        this.Tuteur_nom = Tuteur_nom;
        this.Categorie = Categorie;
        this.duree = duree;
        this.progress = progress;
        this.B = B;
    }

    

    

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getTuteur_nom() {
        return Tuteur_nom;
    }

    public void setTuteur_nom(String Tuteur_nom) {
        this.Tuteur_nom = Tuteur_nom;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public ProgressBar getProgress() {
        return progress;
    }

    public void setProgress(ProgressBar progress) {
        this.progress = progress;
    }

    public Button getB() {
        return B;
    }

    public void setB(Button B) {
        this.B = B;
    }
    
    

    @Override
    public String toString() {
        return "AfficahageMainInterface{" + "Titre=" + Titre + ", Tuteur_nom=" + Tuteur_nom + ", Categorie=" + Categorie + ", duree=" + duree + ", progress=" + progress + ", B=" + B + '}';
    }
    
    
    
    
}
