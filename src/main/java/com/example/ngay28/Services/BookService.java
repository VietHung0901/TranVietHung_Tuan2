package com.example.ngay28.Services;

import com.example.ngay28.Entities.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final List<Book> books;
    public List<Book> getAllBooks() {
        return books;
    }
    public Optional<Book> getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    public void addBook(Book book) {
        books.add(book);
    }
    public void updateBook(Book book) {
        var bookOptional = getBookById(book.getId());
        if (bookOptional.isPresent()) {
            Book bookUpdate = bookOptional.get();
            bookUpdate.setTitle(book.getTitle());
            bookUpdate.setAuthor(book.getAuthor());
            bookUpdate.setPrice(book.getPrice());
            bookUpdate.setCategory(book.getCategory());
        }
    }
    public void deleteBookById(Long id) {
        getBookById(id).ifPresent(books::remove);
    }

    //Tìm kiếm sách
    public List<Book> searchBooks(String title, String author) {
        if (title.trim().isEmpty() && author.trim().isEmpty()) {
            return books; // Trả về toàn bộ danh sách sách
        } else if (title.trim().isEmpty() && !author.trim().isEmpty()) {
            return books.stream()
                    .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                    .collect(Collectors.toList());
        } else if (author.trim().isEmpty() && !title.trim().isEmpty()) {
            return books.stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            return books.stream()
                    .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase())
                            && book.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .collect(Collectors.toList());
        }
    }

    //Gợi ý
    public List<Book> searchBooksTilte(String keyword) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchBooksAuthor(String keyword) {
        return books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

}
