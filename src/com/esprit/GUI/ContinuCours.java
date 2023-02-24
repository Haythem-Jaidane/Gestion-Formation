/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.GUI;

import com.esprit.entities.Cours;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author LENOVO
 */
public class ContinuCours implements EventHandler<ActionEvent>{
    
    private Cours C;
    private String id_utilisateur;
    
    InterfaceCoursController Cn;

    public void setC(Cours C) {
        this.C = C;
    }

    public void setId_utilisateur(String id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setCn(InterfaceCoursController Cn) {
        this.Cn = Cn;
    }
    
    
        
    public void handle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceLireCours.fxml"));
            Parent root = loader.load();
            Cn.getRecharcheText().getScene().setRoot(root);
            
            InterfaceLireCoursController Lire = loader.getController();
            Lire.setId_cours(C.getId());
            Lire.setTitle_cours(C.getTitre());
            Lire.MettreLeCours();
            Lire.setIdutilisateur(id_utilisateur);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
