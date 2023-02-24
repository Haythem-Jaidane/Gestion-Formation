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
public class Progres {
    private String idCours;
    private String idUtlisateur;
    private int progres;
    private int note_examen;
    private boolean isComplete;

    public Progres(String idCours) {
        this.idCours = idCours;
    }
    

    public Progres(String idCours, String idUtlisateur) {
        this.idCours = idCours;
        this.idUtlisateur = idUtlisateur;
    }

    public Progres(String idCours, String idUtlisateur, int progres, int note_examen, boolean isComplete) {
        this.idCours = idCours;
        this.idUtlisateur = idUtlisateur;
        this.progres = progres;
        this.note_examen = note_examen;
        this.isComplete = isComplete;
    }

    public Progres() {
        
    }

    public String getIdCours() {
        return idCours;
    }

    public void setIdCours(String idCours) {
        this.idCours = idCours;
    }

    public String getIdUtlisateur() {
        return idUtlisateur;
    }

    public void setIdUtlisateur(String idUtlisateur) {
        this.idUtlisateur = idUtlisateur;
    }

    public int getProgres() {
        return progres;
    }

    public void setProgres(int progres) {
        this.progres = progres;
    }

    public int getNote_examen() {
        return note_examen;
    }

    public void setNote_examen(int note_examen) {
        this.note_examen = note_examen;
    }

    public boolean isIsComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idCours);
        hash = 37 * hash + Objects.hashCode(this.idUtlisateur);
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
        final Progres other = (Progres) obj;
        if (!Objects.equals(this.idCours, other.idCours)) {
            return false;
        }
        return Objects.equals(this.idUtlisateur, other.idUtlisateur);
    }

    @Override
    public String toString() {
        return "--------------- Progres --------------------\n" + 
               "idCours = " + idCours + "\n"+ 
               "idUtlisateur = " + idUtlisateur + "\n"+
               "progres = " + progres + "\n"+
               "note_examen = " + note_examen + "\n"+
               "isComplete = " + isComplete + "\n"+
               "---------------------------------------------";
    }
    
    
}
