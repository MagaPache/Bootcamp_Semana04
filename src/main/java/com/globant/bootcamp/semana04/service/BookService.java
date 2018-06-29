/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globant.bootcamp.semana04.service;

import com.globant.bootcamp.semana04.model.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.stereotype.Service;
import javax.ws.rs.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Usuario
 */
@Service
@Path("/bookService")
@Consumes("application/json")
@Produces("application/json")
public class BookService {

    private final DataSource dataSource;

    @Autowired
    public BookService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GET
    @Path("/listBooks")
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            Connection conn = this.dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet query = stmt.executeQuery("select * from BOOKS");
            while (query.next()) {
                Book book = new Book();
                book.setId(query.getInt("id"));
                book.setIsbn(query.getInt("isbn"));
                book.setTitle(query.getString("title"));
                book.setPrice(query.getDouble("price"));
                books.add(book);
            }
            query.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }

    @GET
    @Path("/book/{isbn}")
    public Book getOneBook(@PathParam("isbn") int isbn) {
        Book book = new Book();
        try {
            Connection conn = this.dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from BOOKS where isbn =?");
            stmt.setInt(1, isbn);
            ResultSet query = stmt.executeQuery();
            while (query.next()) {
                book.setId(query.getInt("id"));
                book.setIsbn(query.getInt("isbn"));
                book.setTitle(query.getString("title"));
                book.setPrice(query.getDouble("price"));
            }
            query.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return book;
    }
    
    @POST
    @Path("/newBook")
    public void addBook(Book book) throws SQLException {

        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement("insert into BOOKS values(?,?,?)");
        stmt.setInt(1, book.getIsbn());
        stmt.setString(2, book.getTitle());
        stmt.setDouble(3, book.getPrice());
        stmt.executeUpdate();
        stmt.close();
        conn.close();

    }

    @PUT
    @Path("/updateBook")
    public void updateBook(Book book) {

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("update books set title=?, price=? where isbn=?");
            stmt.setString(1, book.getTitle());
            stmt.setDouble(2, book.getPrice());
            stmt.setInt(3, book.getIsbn());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("/deleteBook/{isbn}")
    public void deleteBook(@PathParam("isbn") int isbn) {

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("delete from BOOKS where isbn = ?");
            stmt.setInt(1, isbn);
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
