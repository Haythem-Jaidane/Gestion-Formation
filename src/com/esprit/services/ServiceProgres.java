/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.entities.Progres;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ServiceProgres implements IService<Progres> {
    
    private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Progres P) {
        String req = "INSERT INTO progres(id_cours,id_utilisateur,progres_utilisateur,note_examen,isComplete)"
                     + " VALUES(?,?,?,?,?)";
        try{
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, P.getIdCours());
            st.setString(2, P.getIdUtlisateur());
            st.setInt(3, P.getProgres());
            st.setInt(4, P.getNote_examen());
            st.setBoolean(5, P.isIsComplete());
            st.executeUpdate();
            System.out.println("Progres Ajouter");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Progres P) {
        String req = "UPDATE cours SET progres_utilisateur=?, note_examen=? , isComplete=? "
                     + "WHERE id_cour=? and id_utilisateur=? ;";
        try{
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1,P.getProgres());
            st.setInt(2,P.getNote_examen());
            st.setBoolean(3,P.isIsComplete());
            st.setString(4,P.getIdCours());
            st.setString(5,P.getIdUtlisateur());
            st.executeUpdate();
            System.out.println("Progres Modifier");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Progres p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Progres> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
