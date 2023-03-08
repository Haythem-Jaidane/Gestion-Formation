/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.entities.Contenu;
import com.esprit.entities.Cours;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ServiceContenu implements IService<Contenu> {
    
    private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Contenu C) {
        
        String req = "INSERT INTO contenu(id_contenu,Titre,type,duree,lien_contenu,id_chapitre)"
                     + " VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, C.getId());
            st.setString(2, C.getTitre());
            st.setString(3, C.getType());
            st.setInt(4, C.getDuree());
            st.setString(5, C.getLiencontenu());
            st.setString(6, C.getId_chapitre());
            st.executeUpdate();
            System.out.println("Contenu Ajouter");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Contenu C) {
        String req = "UPDATE contenu SET Titre=?, type=?, duree=? , lien_contenu=? WHERE id_contenu=?;";
        try{
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, C.getType());
            st.setString(2, C.getType());
            st.setInt(3, C.getDuree());
            st.setString(4, C.getLiencontenu());
            st.setString(5, C.getId());
            st.executeUpdate();
            System.out.println("Contenu Modifier");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Contenu C) {
        String req = "DELETE FROM contenu WHERE id_contenu=?;";
        try{
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, C.getId());
            st.executeUpdate();
            System.out.println("Contenu Supprimer");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Contenu> afficher() {
        List<Contenu> listContenu = new ArrayList<>();
        
        String req = "SELECT * FROM contenu";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet result = st.executeQuery();
            while(result.next()) {
                listContenu.add(new Contenu(result.getString("id_contenu"), result.getString("Titre"),
                                            result.getString("type"),result.getInt("duree"),
                                            result.getString("lien_contenu"),result.getString("id_chapitre")
                         ));
            }
            System.out.println("Contenu recuperees !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return listContenu;
    }
    
    
    public List<Contenu> getContenuByChapitre(String Chapitre_id) {
        List<Contenu> listContenu = new ArrayList<>();
        
        String req = "SELECT * FROM contenu where id_chapitre=?";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, Chapitre_id);
            ResultSet result = st.executeQuery();
            while(result.next()) {
                listContenu.add(new Contenu(result.getString("id_contenu"), result.getString("Titre"),
                                            result.getString("type"),result.getInt("duree"),
                                            result.getString("lien_contenu"),result.getString("id_chapitre")
                         ));
            }
            System.out.println("Contenu recuperees !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return listContenu;
    }
}
