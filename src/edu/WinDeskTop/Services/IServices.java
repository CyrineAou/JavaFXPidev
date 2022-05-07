/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.WinDeskTop.Services;

import java.util.List;

/**
 *
 * @author cyrine
 * 
 */
public interface IServices <T> {
        public void ajouter(T a);
       public void supprimer(int id);
       public void modifier(T a);
       public List<T> getAll();
    
    
}
