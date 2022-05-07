/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidevuser.services;

import edu.pidevuser.entities.Admin;
import edu.pidevuser.entities.User;

import edu.pidevuser.utils.DataSource;
import edu.pidevuser.utils.PassEnc;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 *
 * @author bilel
 */
public  class ProfileUser implements IService<User>{

    Connection cnx = DataSource.getInstance().getCnx();
    
    
    @Override
    public void modifier_password(int id, String password) {
        
        Statement st;
        try {
            st = cnx.createStatement();
            
            String query = "UPDATE user SET password='" + password + "' WHERE id='" + id + "'";
            st.executeUpdate(query);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public User login(String email , String password) throws SQLException{

            
        User u = new User();
        try {
            String req = "SELECT  * FROM `user` WHERE `email` = '" + email +"' AND `password` = '" + password +"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                    u.setId(rs.getInt(1));
                    u.setUsername(rs.getString(2)); 
                    u.setEmail(rs.getString(3));
                    u.setNumTel(rs.getInt(4));
                    u.setFirstName(rs.getString(5)); 
                    u.setLastName(rs.getString(6));
                    u.setPassword(rs.getString(7));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return u;       
    }
    
    
    public String ResetPassword(String email){
    
        //User u = new User();
        String pass = null;
        try {
            String req = "SELECT  * FROM `user` WHERE `email` = '" + email +"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                    
                    pass= rs.getString(7);
                System.out.println(pass);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        
    return pass;
    }
    
 
    
    public boolean check_email (String email ) throws SQLException{
    
        boolean b = false ;
        String req = "SELECT email FROM `user` where `email`= '"+ email+"' ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while ( rs.next() ){
                if(rs.getString("email").equals(email)){
                    return b=true;
                } 
            return b=false;
            }
            
            return b;
    }
    
    public boolean check_banne (String email ) throws SQLException{
    
    
        boolean b = false ;
        String req = "SELECT banned FROM `user` where `email`= '"+ email+"' ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while ( rs.next() ){
                if(rs.getBoolean("banned")== true ){
                    //return b=true;
                    System.out.println("banné");
                    return b= true;
                } else {
                    System.out.println("debanné");
                    return b= false;}
                }
            
            return b;
    }
    
    @Override
    public boolean isAdmin (String email , String password) throws SQLException{
    
        boolean b = false ;
        String req = "SELECT `roles` FROM `user` where `email`= '"+ email+"' and  `password` ='"+ password +"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while ( rs.next() ){
                if(rs.getString("roles").equals("ROLE_ADMIN")){
                    System.out.println("admin");
                    return b=true;
                } else {
                    System.out.println("user");
                    return b=false;
                }
            }
            return b;
    }
    
    
    @Override
    public User getUser(String id){
            
        User u = new User();
            try {
            String req = "SELECT  * FROM `user` WHERE `id` = '" + id +"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                    u.setId(rs.getInt(1));
                    u.setUsername(rs.getString(2)); 
                    u.setEmail(rs.getString(3));
                    u.setNumTel(rs.getInt(4));
                    u.setFirstName(rs.getString(5)); 
                    u.setLastName(rs.getString(6));
                    u.setPassword(rs.getString(7));
                    u.setBanned(rs.getBoolean(8));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return u;       
    }
    
    
    public String encrypted(String password){
        
        String encryptedpassword = null;
        try   
        {  
            
            MessageDigest m = MessageDigest.getInstance("MD5");  
            m.update(password.getBytes());             
            byte[] bytes = m.digest();  
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
            password= s.toString();
        }   
        catch (NoSuchAlgorithmException e)   
        {  
            //e.printStackTrace();  
        }  
          
      
        return password;
    }
    @Override
    public void Register(User u) throws SQLException {
        
            String req = "INSERT INTO `user`( `username`, `email`, `tel`, `first_name`, `last_name`, `password`, `roles`, `banned`) "
                    + "                VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            JSONArray roles = new JSONArray();
                roles.add("ROLE_ADMIN");
                roles.add("ROLE_USER");
            if (u instanceof Admin){
                ps.setString(1, u.getUsername());
                ps.setString(2, u.getEmail());
                ps.setInt(3, u.getNumTel());
                ps.setString(4, u.getFirstName());
                ps.setString(5, u.getLastName());
                
                ps.setString(6, this.encrypted(u.getPassword()));
                ps.setString(7, roles.get(0).toString());
                
                
            } else if(u instanceof User){
                ps.setString(1, u.getUsername());
                ps.setString(2, u.getEmail());
                ps.setInt(3, u.getNumTel());
                ps.setString(4, u.getFirstName());
                ps.setString(5, u.getLastName());
                ps.setString(6, u.getPassword());
                ps.setString(7, roles.get(1).toString());
                ps.setBoolean(8, false);
            }
            ps.executeUpdate();
            System.out.println("Votre profil a été enregistré!");
              
    }

    public boolean whenMatchesDigitsNumber_thenCorrect(String tel) {
        return Pattern.compile("^\\d{8}$").matcher(tel).matches();
    }
    
    
    @Override
    public void ModifierUser(User u ) {
        try {
            String req = "UPDATE `user` SET `username`=?,`email`=?,`tel`=?,`first_name`=? "
                    + ", `last_name`=? where id = '" + u.getId() +"'";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmail());
            ps.setInt(3, u.getNumTel());
            ps.setString(4, u.getFirstName());
            ps.setString(5, u.getLastName());

            ps.executeUpdate();
            System.out.println("Profile a été modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    @Override
    public List<User> ListerUsers() {return null;}

    @Override
    public void SupprimerUser(int id) {}

    @Override
    public User findUsingIterator(String username, List<User> Users) {return null;    }

    @Override
    public void find(String username, List<User> Users) {    }

    @Override
    public void sortedList(List<User> Users) {    }

    @Override
    public void reverseOrder(List<User> Users) {    }

    @Override
    public void countUsers(List<User> Users) {    }



    
    
}
