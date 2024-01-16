package springframework.spring6webapp.domain.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframework.spring6webapp.domain.Author;
import springframework.spring6webapp.domain.Book;
import springframework.spring6webapp.domain.Publisher;
import springframework.spring6webapp.repositories.AuthorRepository;
import springframework.spring6webapp.repositories.BookRepository;
import springframework.spring6webapp.repositories.PublisherRepository;

@Component  //annotation means I want it to be a Spring component (Spring will detect it and then it will create
//a Spring bean for it)
public class BootstrapData implements CommandLineRunner {//IF provided by Spring Boot, when it detects this type
    //of component on classpath or in this context
    //so it is going to get picked up and run every time that Spring Boot starts up
    //if it finds a class in this context that implement CLR - its going to execute that

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }//Spring Data JPA is giving us the implementation for AuthorRepository and BookRepository
    //I just wrote the IF where that Spring data provides the actual implementation
    ///=> those are loaded in the context as a Spring bean

    //When Spring detects this component, it sees a contructor says that: "Oh, I have to autowire these components in."

    @Override
    public void run(String... args) {//must be implemented when using CommandLineRunner
        Author author = new Author();
        author.setFirstName("Tvoje");
        author.setLastName("Mama");

        Book book = new Book();
        book.setTitle("MCBP");
        book.setIsbn("111");

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Velke Zvire");
        publisher.setAddress("Vsudezdejsi");
        publisher.setCity("MestoKtereNikdyNEspi");
        publisher.setState("Stoji");
        publisher.setZip("OdBundy");

        //when I save that author - a new object is returned by repository IF
        Author authorSaved = authorRepository.save(author);
        Book bookSaved = bookRepository.save(book);
        Publisher publisherSaved = publisherRepository.save(publisher);

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

        bookSaved.setPublisher(publisherSaved);
        book2Saved.setPublisher(publisherSaved);

        //I added books,...but I did not persist them, so I am adding it now
        authorRepository.save(authorSaved);
        authorRepository.save(author2Saved);//without those 2 lines I would not hve persisted the book association

        bookRepository.save(bookSaved);
        bookRepository.save(book2Saved);


        System.out.println("in Bootstrap");
        System.out.println("Authors count: " + authorRepository.count());
        System.out.println("Books count: " + bookRepository.count());
        System.out.println("Publishers count: " + publisherRepository.count());
    }
}
