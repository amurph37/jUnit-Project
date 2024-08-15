package org.example;

import org.example.Book;
import org.example.BookService;
import org.example.User;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private BookService bookService;
    private User mockUser;
    private Book mockBook;

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("Starting BookService tests...");
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("BookService tests completed.");
    }

    @BeforeEach
    void setUp() {
        bookService = new BookService();
        mockUser = mock(User.class);
        mockBook = mock(Book.class);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Cleaning up...");
    }

    // Test for book search

    @Test
    void testSearchBook_Successful() {
        Book book = new Book("1984", "George Orwell", "Dystopian", 9.99);
        bookService.addBook(book);
        List<Book> results = bookService.searchBook("1984");
        assertEquals(1, results.size(), "One book should be found.");
    }

    @Test
    void testSearchBook_Failure_NoResults() {
        List<Book> results = bookService.searchBook("NonExistentBook");
        assertEquals(0, results.size(), "No books should be found.");
    }

    @Test
    void testSearchBook_EdgeCase_EmptyKeyword() {
        List<Book> results = bookService.searchBook("");
        assertEquals(0, results.size(), "No books should be found when searching with an empty keyword.");
    }

    // Test for book purchase

    @Test
    void testPurchaseBook_Successful() {
        Book book = new Book("1984", "George Orwell", "Dystopian", 9.99);
        bookService.addBook(book);
        assertTrue(bookService.purchaseBook(mockUser, book), "Book purchase should be successful.");
    }

    @Test
    void testPurchaseBook_Failure_NotInDatabase() {
        Book book = new Book("Unknown Book", "Unknown Author", "Unknown", 0.0);
        assertFalse(bookService.purchaseBook(mockUser, book), "Book purchase should fail because the book is not in the database.");
    }

    @Test
    void testPurchaseBook_EdgeCase_EmptyDatabase() {
        assertFalse(bookService.purchaseBook(mockUser, mockBook), "Book purchase should fail because the database is empty.");
    }
}
