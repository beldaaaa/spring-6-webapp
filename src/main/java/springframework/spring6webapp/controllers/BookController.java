package springframework.spring6webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springframework.spring6webapp.services.BookService;

@Controller
public class BookController {
    private final BookService bookService;
    //during runtime Spring sees I require a BookService, its going to have aSpring bean
    //that implements the BookServiceImpl, thats going to be an impl of BookService
    //so it will qualify as dependency candidate for it

    //And when Spring creates this controller component, it will go ahead and wire that in automatically


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/books")//spring will register books, so when I go to 8080/books Spring knows
    //to invoke this method and provide that result
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.findAll());//it tells Spring Boot to look for
        // a VIEW called books, going to bookService, findAll will call bookRepository, that is provided by
        //Spring Data JPA that is going through in conjunction with Hibernate to go through DB and
        //get list of all books in DB then return that value
        //So the Spring fwk is going to be passing that model into getBooks to find all operations
        //after That Thymeleaf will process that template using the template language
        //in books.html line 16 iterator is set to go through and replace the text "661,... and it will occur for each book
        return "books";
        //default location where its going to start finding is resources-templates-...
    }
}
