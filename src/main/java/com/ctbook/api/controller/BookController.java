package com.ctbook.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.ctbook.api.models.Book;
import com.ctbook.api.models.StatusBook;



@RestController
@RequestMapping("/books")
public class BookController {
    private List<Book> books = new ArrayList<>();

    public BookController(){
        books.add(
            new Book(UUID.randomUUID(), "Sítio do Pica Pau Amarelo", "Montero Lobato", "Companhia Editora Nacional", "1939", "Fantasy", StatusBook.Available)
        );
    }

    // GET - initialization
    @GetMapping("/")
    public String home(){
        return "Welcome to Book API";
    }

    // GET - return all books
    @GetMapping
    public List<Book> listAllBooks(){
        return books;
    }

    // GET - search by ID
    @GetMapping("/{id}")
    public Book listById(@PathVariable UUID id){
        for (Book book : books){
            if(book.getId().equals(id)){
                return book;
            }
        }

        return null;
    }

    // POST - register new book
    @PostMapping("/register")
    public Book registerBook(@RequestBody Book book) {
        if (book.getId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book id required");
        }

        book.setId(UUID.randomUUID());
        books.add(book);
        
        return book;
    }
    
    // PUT - update book
    @PutMapping("/update/{id}")
    public Book updateBook(@PathVariable UUID id, @RequestBody Book updatedBook){
        for (Book book : books){
            if(id.equals(book.getId())){
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPublisher(updatedBook.getPublisher());
                book.setYear(updatedBook.getYear());
                book.setCategory(updatedBook.getCategory());
                book.setStatus(updatedBook.getStatus());

                return book;
            }
        }

        return null;
    }

    // DELETE - delete book
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable UUID id){
        for (Book book : books){
            if (id.equals(book.getId())){
                books.remove(book);

                return "Book successfully deleted";
            }
        }

        return "Book not found!";
    }

    
}
