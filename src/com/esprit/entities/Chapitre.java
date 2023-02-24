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
public class Chapitre {
    private String id;
    private String titre;
    private String id_cours;

    public Chapitre(String id) {
        this.id = id;
    }

    public Chapitre(String titre, String id_cours) {
        this.titre = titre;
        this.id_cours = id_cours;
    }
    
    

    public Chapitre(String id, String titre, String id_cours) {
        this.id = id;
        this.titre = titre;
        this.id_cours = id_cours;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getIdcours() {
        return id_cours;
    }
    public void setIdcours(String id_cours) {
        this.id_cours = id_cours;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Chapitre other = (Chapitre) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "------------- Chapitre ---------------\n" + 
                "id = " + id + "\n"+
                "titre = " + titre +"\n"+ 
                "id_cours = " + id_cours + '\n'+
                "--------------------------------------";
    }
    
    
}
