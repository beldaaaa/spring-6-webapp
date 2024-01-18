package springframework.spring6webapp.services;

import org.springframework.stereotype.Service;
import springframework.spring6webapp.domain.Book;
import springframework.spring6webapp.repositories.BookRepository;

@Service//Spring will see it again as component, service
public class BookServiceImpl implements BookService{

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        //BookRepository will be injected by the Spring framework and be available for my use
        //(for ex. for controller, which gets instance of the BookService injected into it for its needs)
    }

    private final BookRepository bookRepository;

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }
}
