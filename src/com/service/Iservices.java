/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Yousra
 */
public interface Iservices<T> {
    public abstract void add (T t) throws SQLException;
    List<T> create() throws SQLException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
    
}
