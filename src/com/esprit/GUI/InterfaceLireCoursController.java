/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.GUI;

import com.esprit.entities.Chapitre;
import com.esprit.entities.Contenu;
import com.esprit.entities.Cours;
import com.esprit.entities.Progres;
import com.esprit.services.ServiceChapitre;
import com.esprit.services.ServiceContenu;
import com.esprit.services.ServiceCours;
import com.esprit.services.ServiceProgres;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class InterfaceLireCoursController implements Initializable {

    @FXML
    private VBox chapter_continer;
    @FXML
    private Button Retour;
    @FXML
    private Label Title;
    
    @FXML
    private VBox course_contenu;
    
    ServiceCours spCours = new ServiceCours();
    ServiceChapitre spChapitre = new ServiceChapitre();
    ServiceContenu spContenu = new ServiceContenu();
    ServiceProgres spProgress = new ServiceProgres();
    
    private String id_cours;
    private String title_cours;
    private String id_utilisateur;

    public void setIdutilisateur(String id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
    
    
    

    public void setTitle_cours(String title_cours) {
        this.title_cours = title_cours;
    }
    

    public void setId_cours(String id_cours) {
        this.id_cours = id_cours;
    }
    
    public void MettreLeCours(){
        Title.setText(title_cours);
        List<Chapitre> L = spChapitre.getChapterByCours(id_cours);
        
        for(Chapitre C:L){
            TitledPane T = new TitledPane();
            T.setText(C.getTitre());
            
            List<Contenu> Con=spContenu.getContenuByChapitre(C.getId());
            
            int i=1;
            for(Contenu O:Con){
                Hyperlink H = new Hyperlink(O.getTitre());
                T.setContent(H);
                H.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        Label l = new Label(O.getTitre());
                        Button But = new Button("lesson terminer");
                        course_contenu.getChildren().add(l);

                        if(O.getType().equals("Video")){
                            File mediaFile = new File(System.getProperty("user.dir")+"src\\com\\esprit\\service\\tmp\\"+id_cours+"\\"+C.getId()+"\\"+O.getTitre()+".mp4");
                            Media media;
                            media = new Media(mediaFile.toURI().toString());
                            MediaPlayer mediaplayer = new MediaPlayer(media);
                            MediaView mediaView = new MediaView (mediaplayer);
                            course_contenu.getChildren().add(mediaView);
                            
                        }
                        else if(O.getType().equals("Texte")){
                            Text t = new Text("");
                            FileReader fr; 
                            String currentDir = System.getProperty("user.dir");
                            System.out.println("Current working directory: " + currentDir);
                            try {
                                fr = new FileReader(System.getProperty("user.dir")+"/src/com/esprit/service/tmp/"+id_cours+"/"+C.getId()+"/"+O.getTitre()+".txt");
                                int i; 
                            try {
                                String D = t.getText();
                                while ((i=fr.read()) != -1){
                                    D= D + (char)i;
                                    System.out.println(D);
                                    
                                }
                                t.setText(D);
                            } catch (IOException ex) {
                                Logger.getLogger(InterfaceLireCoursController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(InterfaceLireCoursController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            course_contenu.getChildren().add(t);
                            
                            
                        }
                        course_contenu.getChildren().add(But);
                        But.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                                Progres P = spProgress.getProgresUtlisateurParCours(id_cours,id_utilisateur);
                                Cours C = spCours.getCoursById(id_cours);
                                //System.out.println(C);
                                int a = (int)(P.getProgres()+((float)O.getDuree()/(float)C.getDuree())*100);
                                P.setProgres(a);
                                System.out.println(a);
                                System.out.println(P.getProgres()+(int)(((float)O.getDuree()/(float)C.getDuree())*100));
                                
                                if(P.getProgres()==1){
                                    P.setIsComplete(true);
                                }
                                spProgress.modifier(P);
                                System.out.println(P.getProgres());
                                But.setOpacity(0);
                            }
                         });
                    }
                });
                
                
                
            }
            
            chapter_continer.getChildren().add(T);
        }
        
        TitledPane T = new TitledPane();
        T.setText("Passer L'examan");
        chapter_continer.getChildren().add(T);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        
    }    

    @FXML
    private void retouner(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceCours.fxml"));
        Parent root = loader.load();
        Retour.getScene().setRoot(root);  
        InterfaceCoursController retour = loader.getController();
    }
    
}
