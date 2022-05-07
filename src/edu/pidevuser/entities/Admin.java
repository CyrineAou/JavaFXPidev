/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.pidevuser.entities;

/**
 *
 * @author bilel
 */
public class Admin extends User {

    public Admin(int id,String username, String email, int numTel, String firstName, String lastName, String password) {
        super(id, username, email, numTel, firstName, lastName, password);
    }

    public Admin() {
    }

    public Admin(String username, String email, int numTel, String firstName, String lastName, String password) {
        super(username, email, numTel, firstName, lastName, password);
    }

    public Admin(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Admin" + super.toString(); 
    }

  
    




    
}
