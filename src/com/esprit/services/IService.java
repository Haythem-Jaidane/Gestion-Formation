/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.esprit.services;

import java.util.List;

/**
 *
 * @author abdel
 */
public interface IService<T> {
    
    public void ajouter(T p);
    public void modifier(T p);
    public void supprimer(T p);
    public List<T> afficher();
}
