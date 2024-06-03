package com.example.ngay28.Controller;


import com.example.ngay28.Entities.Book;
import com.example.ngay28.Services.BookService;
import com.example.ngay28.Services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final CategoryService categoryService;

    @GetMapping
    public String showAllBooks(@NotNull Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book/list";
    }
    @GetMapping("/add")
    public String addBookForm(@NotNull Model model) {
        var categories  = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("book", new Book());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        if(bookService.getBookById(book.getId()).isEmpty())
            bookService.addBook(book);
        return "redirect:/books";
    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@NotNull Model model, @PathVariable long id)
    {
        var categories  = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        var book = bookService.getBookById(id).orElse(null);
        model.addAttribute("book", book != null ? book : new Book());
        return "book/edit";
    }
    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") Book book) {
        bookService.updateBook(book);
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        if (bookService.getBookById(id).isPresent())
            bookService.deleteBookById(id);
        return "redirect:/books";
    }

    //Tìm kiếm trả về view
    @GetMapping("/search")
    public String searchBooksTitle(@RequestParam("title") String title, @RequestParam("author") String author, Model model) {
        List<Book> searchResults = bookService.searchBooks(title,author);
        model.addAttribute("books", searchResults);
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        return "book/list";
    }

    @GetMapping("/search-suggestions-title")
    public ResponseEntity<Map<String, List<String>>> getBookSearchSuggestionsTitle(@RequestParam("keyword") String keyword) {
        List<Book> searchResults = bookService.searchBooksTilte(keyword);
        List<String> bookTitles = searchResults.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());

        Map<String, List<String>> suggestions = new HashMap<>();
        suggestions.put("bookTitles", bookTitles);

        return ResponseEntity.ok(suggestions);
    }

    @GetMapping("/search-suggestions-author")
    public ResponseEntity<Map<String, List<String>>> getBookSearchSuggestionsAuthor(@RequestParam("keyword") String keyword) {
        List<Book> searchResults = bookService.searchBooksAuthor(keyword);

        List<String> authors = searchResults.stream()
                .map(Book::getAuthor)
                .collect(Collectors.toList());

        Map<String, List<String>> suggestions = new HashMap<>();
        suggestions.put("authors", authors);

        return ResponseEntity.ok(suggestions);
    }
}
