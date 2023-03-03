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
public class Contenu {
    private String id;
    private String Titre;
    private String type;
    private int duree;
    private String lien_contenu;
    private String id_chapitre;

    public Contenu(String id) {
        this.id = id;
    }

    public Contenu(String Titre, String type, int duree, String lien_contenu, String id_chapitre) {
        this.Titre = Titre;
        this.type = type;
        this.duree = duree;
        this.lien_contenu = lien_contenu;
        this.id_chapitre = id_chapitre;
    }

    public Contenu(String id, String Titre, String type, int duree, String lien_contenu, String id_chapitre) {
        this.id = id;
        this.Titre = Titre;
        this.type = type;
        this.duree = duree;
        this.lien_contenu = lien_contenu;
        this.id_chapitre = id_chapitre;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }


    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getLiencontenu() {
        return lien_contenu;
    }

    public void setLiencontenu(String lien_contenu) {
        this.lien_contenu = lien_contenu;
    }

    public String getId_chapitre() {
        return id_chapitre;
    }

    public void setId_chapitre(String id_chapitre) {
        this.id_chapitre = id_chapitre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Contenu other = (Contenu) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "----------------- Contenu -------------\n" +
               "id = " + id + "\n"+
               "type =" + type + "\n"+
               "duree = " + duree + "\n"+
               "lien_contenu = " + lien_contenu + "\n"+
               "id_chapitre = " + id_chapitre + "\n"+
               "----------------------------------------";
    }
    
    
}
