/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

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
public class ServiceCours implements IService<Cours> {
    
    private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Cours C) {
        String req = "INSERT INTO cours(id,titre,id_tuteur,categorie,duree,date_de_lancement)"
                     + " VALUES(?,?,?,?,1,SYSDATE())";
        try{
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, C.getId());
            st.setString(2, C.getTitre());
            st.setString(3, C.getTuteur());
            st.setString(4, C.getCategorie());
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
    
}
