package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LibraryController {
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        
        List<Book> books = bookRepository.findAll();
        return books;
    }

    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    
    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRecord borrowRecord) {
        
        Book book = borrowRecord.getBook();
        if (book.getQuantity() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book not available for borrowing");
        }
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecordRepository.save(borrowRecord);
        return ResponseEntity.ok(borrowRecord);
    }

    
    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestBody BorrowRecord borrowRecord) {
        borrowRecord.setReturnDate(LocalDate.now());
        borrowRecordRepository.save(borrowRecord);
        Book book = borrowRecord.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
        return ResponseEntity.ok(borrowRecord);
    }
    
    @GetMapping("/borrowed-books")
    public List<BorrowRecord> getBorrowedBooks() {
        
        List<BorrowRecord> borrowedBooks = borrowRecordRepository.findAll();
        return borrowedBooks;
    }
}
