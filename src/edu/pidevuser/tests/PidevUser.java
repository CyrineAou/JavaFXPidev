/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidevuser.tests;

import edu.pidevuser.entities.Admin;
import edu.pidevuser.entities.User;
import edu.pidevuser.services.ProfileAdmin;
import edu.pidevuser.services.ProfileUser;
import edu.pidevuser.utils.Mail;
import java.sql.SQLException;

/**
 *
 * @author bilel
 */
public class PidevUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, Exception {
      
        User us = new User(30,"Aminee","amin@gmail.com",111,"Amin","Douiri","12345678");
        Admin ad = new Admin("Bileljm","bileljm@gmail.com",45362985,"Bilel","jemai","bilel12345");
        Admin ad1 = new Admin("jarraAziz","aziz@gmail.com",45362985,"Aziz","Jarrar","aziz12345");
        
        ProfileUser pu= new ProfileUser();
        ProfileAdmin pd = new ProfileAdmin();
        Mail m= new Mail();
        //System.out.println(pd.ListerUsers()); 
        //pu.Register(us);
        //pu.Register(ad);
        //pu.ModifierUser(us);
        //pu.Register(ad1);
        //pd.SupprimerUser(24);
        //System.out.println(); 
        //pd.ListerUsers();
        //pd.find("Bileljm", pd.ListerUsers());
        //pd.sortedList(pd.ListerUsers());
        //pd.reverseOrder(pd.ListerUsers());
        //pd.countUsers(pd.ListerUsers());
        //  pu.login("bil@gmail.com", "bilel12345");
        //String s = pu.encrypted("bilel12345");
        //System.out.println(pu.isAdmin("bileljm@gmail.com","bilel12345"));
        //System.out.println(pu.ResetPassword("amin@gmail.com"));
        //m.sendMail("bileljemai97@gmail.com");
        //System.out.println(pu.check_email("amin@gmail.com"));
        
        //System.out.println(pu.isAdmin("bilel@gmail.com","bilel12345"));
        //System.out.println(pu.isAdmin("aziz@gmail.com","aziz12345"));
        //pu.ResetPassword("bileljemai97@gmail.com");
        
        //System.out.println(pd.BanneUser(us));
    }
    
}
