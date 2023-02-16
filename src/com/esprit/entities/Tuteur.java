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
public class Tuteur {
    private String id_tuteur;
    private String id_user;
    private String nom;
    private int cin;
    private String description;

    public Tuteur(String id_tuteur) {
        this.id_tuteur = id_tuteur;
    }

    public Tuteur(String id_tuteur, String id_user, String nom, int cin, String description) {
        this.id_tuteur = id_tuteur;
        this.id_user = id_user;
        this.nom = nom;
        this.cin = cin;
        this.description = description;
    }

    public String getIdtuteur() {
        return id_tuteur;
    }

    public void setIdtuteur(String id_tuteur) {
        this.id_tuteur = id_tuteur;
    }

    public String getIduser() {
        return id_user;
    }

    public void setIduser(String id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id_tuteur);
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
        final Tuteur other = (Tuteur) obj;
        return Objects.equals(this.id_tuteur, other.id_tuteur);
    }

    @Override
    public String toString() {
        return "----------------- Tuteur --------------\n" +
               "id_tuteur = " + id_tuteur + "\n"+ 
               "id_user = " + id_user + "\n"+ 
               "nom = " + nom + "\n"+ 
               "cin = " + cin + "\n"+
               "description = " + description +"\n"+
               "----------------------------------------";
    }
    
    
}
