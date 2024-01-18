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
    //but at this point nothing would happen because I havent provided a VIEW for that yet (later)
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.findAll());//this is effectively like a map and property key
        return "books";
    }
}
