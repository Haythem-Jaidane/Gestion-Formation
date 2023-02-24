/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.entities.Chapitre;
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
public class ServiceChapitre implements IService<Chapitre> {
    
    private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Chapitre C) {
        String req = "INSERT INTO chapitre(id_chapitre,titre,id_cours)"
                     + " VALUES(?,?,?)";
        try{
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, C.getId());
            st.setString(2, C.getTitre());
            st.setString(3, C.getIdcours());
            st.executeUpdate();
            System.out.println("Chapitre Ajouter");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Chapitre C) {
        String req = "UPDATE chapitre SET titre=? WHERE id_chapitre=?;";
        try{
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, C.getTitre());
            st.setString(2, C.getId());
            st.executeUpdate();
            System.out.println("Chapitre Modifier");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Chapitre C) {
        String req = "DELETE FROM chapitre WHERE id_chapitre=?;";
        try{
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, C.getId());
            st.executeUpdate();
            System.out.println("Chapitre Supprimer");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Chapitre> afficher() {
        List<Chapitre> listChapitre = new ArrayList<>();
        
        String req = "SELECT * FROM chapitre";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet result = st.executeQuery();
            while(result.next()) {
                listChapitre.add(new Chapitre(result.getString("id_chapitre"), result.getString("titre"),result.getString("id_cours")));
            }
            System.out.println("Cours recuperees !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return listChapitre;
    }
    
    public List<Chapitre> getChapterByCours(String id_cours) {
        List<Chapitre> listChapitre = new ArrayList<>();
        
        String req = "SELECT * FROM chapitre where id_cours=?";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, id_cours);
            ResultSet result = st.executeQuery();
            while(result.next()) {
                listChapitre.add(new Chapitre(result.getString("id_chapitre"), result.getString("titre"),result.getString("id_cours")));
            }
            System.out.println("chapitre recuperees par cours!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return listChapitre;
    }
    
}
