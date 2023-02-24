/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author LENOVO
 */
public class Cours {
    
    private String id;
    private String titre;
    private String id_tuteur;
    private String categorie;
    private int duree;
    private Date date_lancement;

    public Cours() {
    }
    
    

    public Cours(String id) {
        this.id = id;
    }

    public Cours(String id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    public Cours(String titre, String id_tuteur, String categorie, int duree) {
        this.titre = titre;
        this.id_tuteur = id_tuteur;
        this.categorie = categorie;
        this.duree = duree;
    }

    
    
    

    public Cours(String id, String titre, String tuteur,String categorie) {
        this.id = id;
        this.titre = titre;
        this.id_tuteur = tuteur;
        this.categorie = categorie;
    }

    public Cours(String id, String titre, String tuteur,String categorie, int duree, Date date_lancement) {
        this.id = id;
        this.titre = titre;
        this.id_tuteur = tuteur;
        this.categorie = categorie;
        this.duree = duree;
        this.date_lancement = date_lancement;
    }

    public String getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getTuteur() {
        return id_tuteur;
    }

    public int getDuree() {
        return duree;
    }

    public Date getDate_lancement() {
        return date_lancement;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setTuteur(String tuteur) {
        this.id_tuteur = tuteur;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setDate_lancement(Date date_lancement) {
        this.date_lancement = date_lancement;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Cours other = (Cours) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return " --------------- Cours ------------------\n" +
               "id = " + id + "\n"+
               "titre = " + titre +"\n"+
               "tuteur = " + id_tuteur +"\n"+
               "categorie = "+ categorie +"\n"+
               "duree = " + duree +"\n"+
               "date_lancement = " + date_lancement +"\n"+ 
               "-----------------------------------------";
    }
    
    
    
    
    
    
}
