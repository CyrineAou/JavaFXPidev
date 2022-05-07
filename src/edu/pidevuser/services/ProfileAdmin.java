/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidevuser.services;

import edu.pidevuser.entities.Admin;
import edu.pidevuser.entities.User;

import edu.pidevuser.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bilel
 */
public class ProfileAdmin implements IService<User> {

    Connection cnx = DataSource.getInstance().getCnx();
    

    @Override
    public void Register(User u) {
    }

    @Override
    public void SupprimerUser(int id) {
        try {
            String req = "DELETE FROM `user` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("profile a été supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void BanneUser(User u) {
        try {
            String req = "UPDATE `user` SET `banned`= true where `id`  = '" + u.getId() + "'";
            Statement ps = cnx.createStatement();
            //ps.setBoolean(1,u.getBanned());
            ps.executeUpdate(req);
            //System.out.println("Votre profil a été enregistré!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void DebanneUser(User u) {
        try {
            String req = "UPDATE `user` SET `banned`=  false where `id`  = '" + u.getId() + "'";
            Statement ps = cnx.createStatement();
            //ps.setBoolean(1,u.getBanned());
            ps.executeUpdate(req);
            //System.out.println("Votre profil a été enregistré!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        
    }

    @Override
    public ObservableList<User> ListerUsers() {
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM `user`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if ( rs.getString("roles").equals("ROLE_ADMIN")) {
                    Admin a = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));
                    list.add(a);
                } else if ( rs.getString("roles").equals("ROLE_USER")){
                    User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(9));
                     list.add(u);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    

    @Override
    public User findUsingIterator(String username, List<User> Users) {
        
    Iterator<User> iterator = Users.iterator();
    while (iterator.hasNext()) {
        User user = iterator.next();
        if (user.getUsername().equals(username)) {
            user.toString();
            System.out.println("existe");
            return user;
        }
        else 
            System.out.println("n'existe pas");
    
    }
    return null;
    
    }
    
    @Override
    public void find(String username, List<User> Users) {
        User result1 = Users.stream()                        
                .filter(x -> username.equals(x.getUsername()))       
                .findAny()                                      
                .orElse(null);                                  

        System.out.println(result1);
    }
    


    @Override
    public void sortedList( List<User> Users) {
        List<User> sortedList = Users.stream()
			.sorted(Comparator.comparing(User::getUsername))
			.collect(Collectors.toList());
 
    sortedList.forEach(System.out::println);
    }
    
    @Override
    public void reverseOrder( List<User> Users) {
        List<User> reverseOrderList = Users.stream()
			.sorted(Comparator.comparing(User::getUsername)
                        .reversed())
                        .collect(Collectors.toList());

    reverseOrderList.forEach(System.out::println);
    }
    
    @Override
    public void countUsers( List<User> Users){
         long total = Users.stream().count();  
        System.out.println(total);
    }

    @Override
    public boolean isAdmin(String email, String password) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User login(String email, String password) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_password(int id, String password) {    }

    @Override
    public User getUser(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ModifierUser(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}

