package org.example.dailyquiz2.Controller;

import org.example.dailyquiz2.Domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/addBook")
    public String addBook() {
        return "addBook";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Book book) {
        book.setId(nextId++);
        books.add(book);
        return "redirect:/books";
    }

    @PostMapping("/editBook")
    public String editBook(@ModelAttribute Book book) {

        updateBook(book);
        return "redirect:/books";
    }

    @GetMapping("/editBook/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {

        Book findBook = findBookById(id);
        model.addAttribute(findBook);
        return "editBook";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Long id) {
        books.remove(findBookById(id));
        return "redirect:/books";
    }

    private Book findBookById(Long id) {

        Book book = books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 책이 존재하지 않습니다. id: " + id));

        return book;
    }

    private void updateBook(Book book) {
        Book findBook = findBookById(book.getId());
        findBook.setTitle(book.getTitle());
        findBook.setAuthor(book.getAuthor());
        findBook.setPublicationYear(book.getPublicationYear());
    }
}
