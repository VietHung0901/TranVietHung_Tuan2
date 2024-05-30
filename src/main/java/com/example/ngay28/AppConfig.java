package com.example.ngay28;

import com.example.ngay28.Entities.Book;
import com.example.ngay28.Entities.Category;
import com.example.ngay28.Entities.Employer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Configuration

public class AppConfig {
    @Bean
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Lập trình Web Spring Framework", "Ánh Nguyễn", 29.99, "Công nghệ thông tin"));
        books.add(new Book(2L, "Lập trình ứng dụng Java", "Huy Cường", 45.63, "Công nghệ thông tin"));
        books.add(new Book(3L, "Lập trình Web Spring Boot", "Xuân Nhân", 12., "Công nghệ thông tin"));
        books.add(new Book(4L, "Lập trình Web Spring MVC", "Ánh Nguyễn", .12, "Công nghệ thông tin"));
        return books;
    }

    @Bean
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();

        Category category1 = new Category();
        category1.setId(1L);
        category1.setCategory_name("Công nghệ thông tin");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setCategory_name("Kinh tế");

        Category category3 = new Category();
        category3.setId(3L);
        category3.setCategory_name("Y tế");

        Category category4 = new Category();
        category4.setId(4L);
        category4.setCategory_name("Giáo dục");

        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);

        return categories;
    }

    @Bean
    public List<Employer> getEmployers() {
        List<Employer> employers = new ArrayList<>();
        employers.add(new Employer(1L, "Acme Inc.", "acme@example.com", "0123456789", "123 Main St, Anytown USA", new Date(1980, 3, 15)));
        employers.add(new Employer(2L, "Globex Corporation", "globex@example.com", "9876543210", "456 Oak Rd, Someplace State", new Date(1975, 8, 22)));
        employers.add(new Employer(3L, "Stark Industries", "stark@example.com", "5551234567", "789 Elm St, Othertown CA", new Date(1985, 11, 7)));
        employers.add(new Employer(4L, "Wayne Enterprises", "wayne@example.com", "2221112233", "321 Oak Blvd, Metropolis", new Date(1990, 5, 30)));
        return employers;
    }
}
