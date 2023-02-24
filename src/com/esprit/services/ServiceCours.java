/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.entities.Cours;
import com.esprit.entities.Contenu;
import com.esprit.entities.Utilisateur;
import com.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TitledPane;

/**
 *
 * @author LENOVO
 */
public class ServiceCours implements IService<Cours> {
    
    private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Cours C) {
        String req = "INSERT INTO cours(id,titre,id_tuteur,categorie,duree,date_de_lancement)"
                     + " VALUES(?,?,?,?,?,SYSDATE())";
        try{
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, C.getId());
            st.setString(2, C.getTitre());
            st.setString(3, C.getTuteur());
            st.setString(4, C.getCategorie());
            st.setInt(5, C.getDuree());
            st.executeUpdate();
            System.out.println("Cours Ajouter");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Cours C) {
        String req = "UPDATE cours SET titre=?, id_tuteur=? , categorie=? WHERE id=?;";
        try{
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, C.getTitre());
            st.setString(2, C.getTuteur());
            st.setString(3, C.getCategorie());
            st.setString(4, C.getId());
            st.executeUpdate();
            System.out.println("Cours Modifier");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Cours C) {
        String req = "DELETE FROM cours WHERE id=?;";
        try{
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, C.getId());
            st.executeUpdate();
            System.out.println("Cours Supprimer");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Cours> afficher() {
        List<Cours> listCours = new ArrayList<>();
        
        String req = "SELECT * FROM cours";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet result = st.executeQuery();
            while(result.next()) {
                listCours.add(new Cours(result.getString("id"), result.getString("titre"),
                                   result.getString("id_tuteur"),result.getString("categorie"),
                                   result.getInt("duree"),result.getDate("date_de_lancement")
                         ));
            }
            System.out.println("Cours recuperees !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return listCours;
    }
    
    public Utilisateur getNomTuteurAvecId(Cours C){
        
        String req = "SELECT * FROM Utilisateur where id_utilisateur=?";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, C.getTuteur());
            ResultSet result = st.executeQuery();
            while(result.next()) {
                System.out.println("utilsateur avec ID recuperees !");
                return new Utilisateur(result.getString("id_utilisateur"), result.getString("nom"));
            }
            return new Utilisateur();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new Utilisateur();
        }
        
    }
    
    public List<Cours> Rechercher(Cours C){
        List<Cours> listCours = new ArrayList<>();
        
        String req = "SELECT * FROM cours where (lower(titre) LIKE ?);";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, "%"+C.getTitre()+"%");
            ResultSet result = st.executeQuery();
            while(result.next()) {
                listCours.add(new Cours(result.getString("id"), result.getString("titre"),
                                   result.getString("id_tuteur"),result.getString("categorie"),
                                   result.getInt("duree"),result.getDate("date_de_lancement")
                         ));
            }
            System.out.println("Cours rechercher !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return listCours;
    }
    
    public void incrementer(Cours C,Contenu con){
        
        Cours Cou = new Cours();
        String req = "SELECT * FROM cours Where id=?";
        String req1 = "UPDATE cours SET duree=? WHERE id=?;";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, C.getId());
            ResultSet result = st.executeQuery();
            while(result.next()) {
                Cou = new Cours(result.getString("id"), result.getString("titre"),
                                   result.getString("id_tuteur"),result.getString("categorie"),
                                   result.getInt("duree"),result.getDate("date_de_lancement"));
            }
            System.out.println(Cou);
            System.out.println("Duree Incremente !");
            PreparedStatement st1 = cnx.prepareStatement(req1);
            st1.setInt(1, Cou.getDuree()+con.getDuree());
            System.out.println(Cou.getDuree()+con.getDuree());
            st1.setString(2, C.getId());
            st1.executeUpdate();
            System.out.println("Duree Incremente !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
        public Cours getCoursById(String id_cours){
            
            Cours C = new Cours();
            String req = "SELECT * FROM cours where id=?;";
            try {
                PreparedStatement st = cnx.prepareStatement(req);
                st.setString(1, id_cours);
                ResultSet result = st.executeQuery();
                while(result.next()) {
                    C = new Cours(result.getString("id"), result.getString("titre"),
                                   result.getString("id_tuteur"),result.getString("categorie"),
                                   result.getInt("duree"),result.getDate("date_de_lancement"));
                }
                //System.out.println(C);
                return C;
            }
             catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new Cours();
                    }
        }
        
    public List<Cours> afficherParTuteur(String id_tuto) {
        List<Cours> listCours = new ArrayList<>();
        
        String req = "SELECT * FROM cours where id_tuteur=?;";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, id_tuto);
            ResultSet result = st.executeQuery();
            while(result.next()) {
                listCours.add(new Cours(result.getString("id"), result.getString("titre"),
                                   result.getString("id_tuteur"),result.getString("categorie"),
                                   result.getInt("duree"),result.getDate("date_de_lancement")
                         ));
            }
            System.out.println("Cours recuperees tuteur !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return listCours;
    }
}


