/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

/**
 *
 * @author LENOVO
 */
public class Utilisateur {
    
    private String id_utilisateur;
    private String nom;
    private boolean isTuto;

    public Utilisateur() {
    }
    
    public Utilisateur(String id_utilisateur, String nom, boolean isTuto) {
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.isTuto = isTuto;
    }

    public Utilisateur(String id_utilisateur, String nom) {
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
    }

    public Utilisateur(String id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    
    
    

    public String getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(String id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isIsTuto() {
        return isTuto;
    }

    public void setIsTuto(boolean isTuto) {
        this.isTuto = isTuto;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id_utilisateur=" + id_utilisateur + ", nom=" + nom + ", isTuto=" + isTuto + '}';
    }
    
    
    
    
}
