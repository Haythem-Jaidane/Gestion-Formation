/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import com.dropbox.core.DbxException;
import static com.dropbox.core.v2.common.PathRoot.root;
import com.esprit.entities.AffichageConsulter;
import com.esprit.entities.AffichageConsulterChapitre;
import com.esprit.entities.Chapitre;
import com.esprit.entities.Contenu;
import com.esprit.entities.Cours;
import com.esprit.entities.Progres;
import com.esprit.services.ServiceAPIDropbox;
import com.esprit.services.ServiceChapitre;
import com.esprit.services.ServiceContenu;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class InterfaceConsulterChapitreController implements Initializable {


    @FXML
    private Button retourButton;
    @FXML
    private TableView<AffichageConsulterChapitre> chapitreContainer;
    @FXML
    private TableColumn<AffichageConsulterChapitre, String> titre;
    @FXML
    private TableColumn<AffichageConsulterChapitre, Button> supp;
    @FXML
    private TableColumn<AffichageConsulterChapitre, Button> Contenu;
    @FXML
    private Button RetourCours;
    @FXML
    private Button ajouterChapitre;
    
    private String idCours;
    private String id_tuto;
    
    private ServiceChapitre spChapitre = new ServiceChapitre();
    private ServiceContenu spContenu = new ServiceContenu();
    private List<Chapitre> u;
    
    
    ServiceAPIDropbox dropbox ;

    
    

    public void setIdCours(String idCours) {
        this.idCours = idCours;
    }

    public void setId_tuto(String id_tuto) {
        this.id_tuto = id_tuto;
    }
    
    public void setTableView() throws DbxException{
        
        dropbox = new ServiceAPIDropbox();
        
        List<AffichageConsulterChapitre> k = new ArrayList<>();
        
        System.out.println(idCours);
        
        u = spChapitre.getChapterByCours(idCours);
        System.out.println(u);
        
                
        titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        titre.setCellFactory(TextFieldTableCell.forTableColumn());
        
        supp.setCellValueFactory(new PropertyValueFactory<>("Supp"));
        Contenu.setCellValueFactory(new PropertyValueFactory<>("Con"));

        
        
        
        for(Chapitre i:u){
            
            Button B = new Button("Supprimer");
            Button Chap = new Button("Voir les Contenu");
            
            B.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            
                            public void handle(ActionEvent e) {
                                Dialog<String> dialog = new Dialog<String>();
                                dialog.setTitle("Chapitre Supprimer");
                                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                dialog.setContentText("le Chapitre "+ i.getTitre()+" à été supprimé");
                                dialog.getDialogPane().getButtonTypes().add(type);
        
                                List<Contenu> C = spContenu.getContenuByChapitre(i.getId());
                                for(Contenu j:C){
                                    spContenu.supprimer(j); 
                                }
                                
                                try {
                                    dropbox.supprimerFolder(idCours+"/"+i.getId());
                                } catch (DbxException ex) {
                                    Logger.getLogger(InterfaceConsulterChapitreController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceConsulterChapitre.fxml"));
                                Parent root;
                                try {
                                    root = loader.load();
                                    retourButton.getScene().setRoot(root);  
                                    InterfaceConsulterChapitreController consultarChapitre = loader.getController();
                                    consultarChapitre.setIdCours(i.getId());
                                    consultarChapitre.setId_tuto(id_tuto);
                                    consultarChapitre.setTableView();
                                    dialog.showAndWait();
                                } catch (IOException ex) {
                                    Logger.getLogger(InterfaceConsulterCourController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (DbxException ex) {
                                    Logger.getLogger(InterfaceConsulterCourController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                        
                                spChapitre.supprimer(i);
                            }
                         });
            
            Chap.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            
                            public void handle(ActionEvent e) {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceConsulterContenu.fxml"));
                                Parent root;
                                try {
                                    root = loader.load();
                                    retourButton.getScene().setRoot(root);  
                                    InterfaceConsulterContenuController consultarChapitre = loader.getController();
                                    consultarChapitre.setId_chapitre(i.getId());
                                    consultarChapitre.setId_tuto(id_tuto);
                                    consultarChapitre.setId_cours(idCours);
                                    consultarChapitre.setTableView();
                                } catch (IOException ex) {
                                    Logger.getLogger(InterfaceConsulterCourController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (DbxException ex) {
                                    Logger.getLogger(InterfaceConsulterChapitreController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            }
                         });
           
            AffichageConsulterChapitre ajou = new AffichageConsulterChapitre(i.getTitre(),B,Chap);
            k.add(ajou);
        }
        
        chapitreContainer.getItems().addAll(k);
        chapitreContainer.setEditable(true);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    @FXML
    private void RetournerP(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceCours.fxml"));
        Parent root = loader.load();
        retourButton.getScene().setRoot(root);  
        InterfaceCoursController ajouter = loader.getController();
    }

    @FXML
    private void RetournerCours(MouseEvent event) throws IOException, DbxException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceConsulterCour.fxml"));
        Parent root = loader.load();
        chapitreContainer.getScene().setRoot(root);  
        InterfaceConsulterCourController ajouter = loader.getController();
        ajouter.setId_tuto(id_tuto);
        ajouter.setTableView();
    }

    @FXML
    private void ajouterUnChapitre(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceAjouterChapitre.fxml"));
        Parent root = loader.load();
        chapitreContainer.getScene().setRoot(root);  
        InterfaceAjouterChapitreController Chapitre = loader.getController();
        Chapitre.setId_Cours(idCours);
    }

}
