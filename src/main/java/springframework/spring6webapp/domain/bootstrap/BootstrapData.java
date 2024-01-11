package springframework.spring6webapp.domain.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframework.spring6webapp.domain.Author;
import springframework.spring6webapp.domain.Book;
import springframework.spring6webapp.repositories.AuthorRepository;
import springframework.spring6webapp.repositories.BookRepository;

@Component //annotation means I want it to be a Spring component (Spring will detect it and pick it up)
public class BootstrapData implements CommandLineRunner {//IF provided by Spring Boot, when it detects this type of component on classpath or in this context
    //so it is going to get picked up and run every time that Spring Boot starts up
    //if it finds a class in this context that implement CLR - its going to execute that

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author author = new Author();
        author.setFirstName("Tvoje");
        author.setLastName("Mama");

        Book book = new Book();
        book.setTitle("MCBP");
        book.setIsbn("111");

        //when I save that author - a new object is returned by repository IF
        Author authorSaved = authorRepository.save(author);
        Book bookSaved = bookRepository.save(book);


        Author author2 = new Author();
        author.setFirstName("Tvoje");
        author.setLastName("Mama");

        Book book2 = new Book();
        book.setTitle("MCBP");
        book.setIsbn("111");

        Author author2Saved = authorRepository.save(author2);
        Book book2Saved = bookRepository.save(book2);

        authorSaved.getBooks().add(bookSaved);//building an association between created book and created author
        author2Saved.getBooks().add(book2Saved);

        System.out.println("in Bootstrap");
        System.out.println("Authors count: "+ authorRepository.count());
        System.out.println("Books count: "+ bookRepository.count());
    }
}
