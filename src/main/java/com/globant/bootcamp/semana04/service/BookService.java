/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globant.bootcamp.semana04.service;

import com.globant.bootcamp.semana04.model.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import javax.ws.rs.*;

/**
 *
 * @author Usuario
 */
@Service
@Path("/bookService")
@Consumes("application/json")
@Produces("application/json")
public class BookService {
    
    
    @GET
    @Path("/listBooks")
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookShop", "sa", "Server1552");
            Statement stmt = conn.createStatement();
            ResultSet query = stmt.executeQuery("select * from BOOKS");
            while(query.next()){
                Book book = new Book();
                book.setId(query.getInt("id"));
                book.setIsbn(query.getInt("isbn"));
                book.setTitle(query.getString("title"));
                book.setPrice(query.getDouble("price"));
                books.add(book);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }
    
    
    
}
