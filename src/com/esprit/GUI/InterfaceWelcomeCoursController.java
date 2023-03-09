/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import com.dropbox.core.DbxException;
import com.esprit.entities.Progres;
import com.esprit.services.ServiceAPIDropbox;
import com.esprit.services.ServiceProgres;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class InterfaceWelcomeCoursController implements Initializable {

    @FXML
    private Circle Cyc;
    @FXML
    private Rectangle sqr;
    @FXML
    private Label coursName;
    
    private String cour_id;
    private String cour_name;
    private String id_utlisateur;
    
    ServiceProgres spProgres = new ServiceProgres();
    ServiceAPIDropbox spDropbox;
    

    public void setCour_id(String cour_id) {
        this.cour_id = cour_id;
    }

    public void setCour_name(String cour_name) {
        this.coursName.setText(cour_name);
    }

    public void setId_utlisateur(String id_utlisateur) {
        this.id_utlisateur = id_utlisateur;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
            spDropbox = new ServiceAPIDropbox();
        } catch (DbxException ex) {
            Logger.getLogger(InterfaceWelcomeCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new java.util.Timer().schedule( 
        new java.util.TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
            // Code that updates the UI goes here
        
                try {
                    spProgres.ajouter(new Progres(cour_id,id_utlisateur,0,0,false));
                    try {
                        spDropbox.DownloadTmp(cour_id);
                    } catch (DbxException ex) {
                        Logger.getLogger(InterfaceWelcomeCoursController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceLireCours.fxml"));
                    Parent root = loader.load();
                    coursName.getScene().setRoot(root);
                    InterfaceLireCoursController Lire = loader.getController();
                    Lire.setId_cours(cour_id);
                    Lire.setTitle_cours(cour_name);
                    Lire.MettreLeCours();
                } catch (IOException ex) {
                    Logger.getLogger(InterfaceWelcomeCoursController.class.getName()).log(Level.SEVERE, null, ex);
                }
                });
            }
        }, 
        5000 
        );

        
        RotateTransition rotateTransition = new RotateTransition();
        ScaleTransition scaleTransition = new ScaleTransition(); 
        
        scaleTransition.setDuration(Duration.millis(1000));
        rotateTransition.setDuration(Duration.millis(1000)); 
        
        rotateTransition.setNode(sqr);
        scaleTransition.setNode(Cyc);
        
        rotateTransition.setByAngle(360);   
        scaleTransition.setByY(0.5); 
        scaleTransition.setByX(0.5); 
        
        rotateTransition.setCycleCount(50); 
        scaleTransition.setCycleCount(50);
       
        rotateTransition.setAutoReverse(false); 
        scaleTransition.setAutoReverse(false); 
       
        rotateTransition.play();
        scaleTransition.play();
        
        
       
    }    
    
}
