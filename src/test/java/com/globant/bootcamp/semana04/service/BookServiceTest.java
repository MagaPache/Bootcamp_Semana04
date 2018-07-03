/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.globant.bootcamp.semana04.service;

import com.globant.bootcamp.semana04.model.Book;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Usuario
 */
public class BookServiceTest {

    public BookServiceTest() {
    }

//    @Autowired DataSource dataSource;
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Mock
    Book book = new Book();

//private final DataSource dataSource;
    /**
     * Test of getAllBooks method, of class BookService.
     */
    @Test(expected = java.lang.NullPointerException.class)
    public void shouldThrowNullPointerExceptionAndDoNotGetAllBooks() {
        System.out.println("getAllBooks");
        BookService instance = null;
        //BookService instance = new BookService(); 
        List<Book> result = instance.getAllBooks();
        assertNull(result);

    }

    /**
     * Test of getOneBook method, of class BookService.
     */
    @Ignore
    @Test
    public void shouldGetOneBook() {
        System.out.println("getOneBook");
        int isbn = 1;
        BookService instance = null;
        //BookService instance = new BookService(dataSource);
        Book result = instance.getOneBook(isbn);
        assertNotNull(result);
        assertEquals("Successfuly get a book from the table", "Book", result);
        
    }
    
    @Ignore
    @Test(expected= NumberFormatException.class)
    public void shouldThrowAnumberFormatException() {
        System.out.println("getOneBook");
        String isbn = "uno";
        BookService instance = null;
        //BookService instance = new BookService(dataSource);
        Book result = instance.getOneBook(Integer.parseInt(isbn));
        
    }

    /**
     * Test of addBook method, of class BookService.
     */
    @Ignore
    @Test
    public void shouldAddAnewBook() throws Exception {
        System.out.println("addBook");
        BookService instance = null;
        //BookService instance = new BookService();
        instance.addBook(book);
        
    }

    

}
