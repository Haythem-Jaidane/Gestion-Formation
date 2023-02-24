/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

import java.util.Objects;

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
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id_utilisateur);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilisateur other = (Utilisateur) obj;
        return Objects.equals(this.id_utilisateur, other.id_utilisateur);
    }
    
    

    @Override
    public String toString() {
        return "Utilisateur{" + "id_utilisateur=" + id_utilisateur + ", nom=" + nom + ", isTuto=" + isTuto + '}';
    }
    
    
    
    
}
