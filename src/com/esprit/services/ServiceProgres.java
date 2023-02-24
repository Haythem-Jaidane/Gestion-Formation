/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.entities.Cours;
import com.esprit.entities.Progres;
import com.esprit.entities.Utilisateur;
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
        String req = "UPDATE progres SET progres_utilisateur=?, note_examen=? , isComplete=? "
                     + "WHERE id_cours=? and id_utilisateur=? ;";
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
    public void supprimer(Progres P) {
        String req = "DELETE FROM progres WHERE id_cours=? and id_utilisateur=?;";
        try{
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1,P.getIdCours());
            st.setString(2,P.getIdUtlisateur());
            st.executeUpdate();
            System.out.println("Progres Supprimer");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Progres> afficher() {
        List<Progres> listProgres = new ArrayList<>();
        
        String req = "SELECT * FROM progres";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet result = st.executeQuery();
            while(result.next()) {
                listProgres.add(new Progres(result.getString("id_cours"),result.getString("id_utilisateur"),
                                   result.getInt("progres_utilisateur"),result.getInt("note_examen"),
                                   result.getBoolean("isComplete")
                         ));
            }
            System.out.println("progres recuperees !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return listProgres;
    }
    
    public Progres getProgresUtlisateurParCours(Cours C,Utilisateur U){
        String req = "SELECT * FROM progres where id_utilisateur=? and id_cours=?";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, U.getId_utilisateur());
            st.setString(2, C.getId());
            ResultSet result = st.executeQuery();
            while(result.next()) {
                System.out.println("progres avec ID recuperees !");
                return new Progres(result.getString("id_Cours"), result.getString("id_utilisateur"),
                                   result.getInt("progres_utilisateur"), result.getInt("note_examen"),
                                   result.getBoolean("isComplete"));
            }
            return new Progres();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new Progres();
        }
    }
    
    public Progres getProgresUtlisateurParCours(String C,String U){
        String req = "SELECT * FROM progres where id_utilisateur=? and id_cours=?";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, U);
            st.setString(2, C);
            ResultSet result = st.executeQuery();
            while(result.next()) {
                System.out.println("progres avec ID recuperees !");
                return new Progres(result.getString("id_Cours"), result.getString("id_utilisateur"),
                                   result.getInt("progres_utilisateur"), result.getInt("note_examen"),
                                   result.getBoolean("isComplete"));
            }
            return new Progres();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new Progres();
        }
    }
    
    
    public boolean checkProgresUtlisateurParCours(Cours C,Utilisateur U){
        String req = "SELECT * FROM progres where id_utilisateur=? and id_cours=?";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, U.getId_utilisateur());
            st.setString(2, C.getId());
            ResultSet result = st.executeQuery();
            while(result.next()) {
                System.out.println("progres avec ID recuperees !");
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public void supprimerParCours(Progres P) {
        String req = "DELETE FROM progres WHERE id_cours=?;";
        try{
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1,P.getIdCours());
            st.executeUpdate();
            System.out.println("Progres Supprimer");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
