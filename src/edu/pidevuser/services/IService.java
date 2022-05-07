/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidevuser.services;

import edu.pidevuser.entities.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author bilel
 * @param <T>
 */
public interface IService <T> {
    public void Register (T u) throws SQLException;
    public void SupprimerUser(int id);
    public void ModifierUser(T u);
    public List<T> ListerUsers();
    public T    findUsingIterator(String username, List<T> Users);
    public void find(String username, List<T> Users);
    public void sortedList( List<T> Users);
    public void reverseOrder( List<T> Users);
    public void countUsers( List<T> Users);
    public boolean isAdmin (String email , String password)throws SQLException;
    public User login(String email , String password) throws SQLException;
    public void modifier_password(int id, String password);
    public User getUser(String id);
    
}
