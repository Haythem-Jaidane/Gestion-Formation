/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.entities.Tuteur;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ServiceTuteur implements IService<Tuteur> {
    
    private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Tuteur T) {
        String req = "INSERT INTO progres(id_tuteur,id_user,nom,CIN,Description)"
                     + " VALUES(?,?,?,?,?)";
        try{
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, T.getIdtuteur());
            st.setString(2, T.getIduser());
            st.setString(3, T.getNom());
            st.setInt(4, T.getCin());
            st.setString(5, T.getDescription());
            st.executeUpdate();
            System.out.println("Tuteur Ajouter");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Tuteur T) {
        String req = "UPDATE cours SET nom=?, CIN=? , Description=? WHERE id_tuteur=?;";
        try{
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, T.getNom());
            st.setInt(2, T.getCin());
            st.setString(3, T.getDescription());
            st.setString(4, T.getIdtuteur());
            st.executeUpdate();
            System.out.println("Tuteur Modifier");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Tuteur p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Tuteur> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
