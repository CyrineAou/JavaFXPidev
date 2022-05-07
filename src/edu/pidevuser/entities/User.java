/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidevuser.entities;

/**
 *
 * @author bilel
 */
public class User {
    
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int numTel;
    private String username;
    private Boolean banned;

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public User(Boolean banned) {
        this.banned = banned;
    }
    
    

    public User(int id , String username,String email ,  int numTel , String firstName, String lastName,   String password, Boolean banned ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.username = username;
        this.banned = banned;
    }
    public User(int id , String username,String email ,  int numTel , String firstName, String lastName,   String password ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.username = username;
    }

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(String username ,String email ,  int numTel , String firstName, String lastName ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.numTel = numTel;
        this.username = username;
    }
    

    
    public User( String username,String email ,  int numTel , String firstName, String lastName,   String password , Boolean banned) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.username = username;
    }
    
    public User( String username,String email ,  int numTel , String firstName, String lastName,   String password ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", numTel=" + numTel + ", username=" + username + ", banned=" + banned + '}';
    }

    
    
    
   
}
