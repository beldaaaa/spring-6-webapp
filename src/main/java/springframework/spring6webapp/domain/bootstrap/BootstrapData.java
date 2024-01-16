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
        Author author1 = new Author();
        author1.setFirstName("Tvoje");
        author1.setLastName("Mama");

        Book book1 = new Book();
        book1.setTitle("MCBP");
        book1.setIsbn("111");

        Author author2 = new Author();
        author2.setFirstName("Cudriho");
        author2.setLastName("Mama");

        Book book2 = new Book();
        book2.setTitle("CMCBP");
        book2.setIsbn("C111");

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Velke Zvire");
        publisher.setAddress("Vsudezdejsi");
        publisher.setCity("MestoKtereNikdyNEspi");
        publisher.setState("Stoji");
        publisher.setZip("OdBundy");

        //when I save that author - a new object is returned by repository IF
        Author author1Saved = authorRepository.save(author1);
        Book book1Saved = bookRepository.save(book1);

        Author author2Saved = authorRepository.save(author2);
        Book book2Saved = bookRepository.save(book2);
        Publisher publisherSaved = publisherRepository.save(publisher);

        author1Saved.getBooks().add(book1Saved);//building an association between created book and created author
        author2Saved.getBooks().add(book2Saved);
        book1Saved.getAuthors().add(author1Saved);
        book2Saved.getAuthors().add(author2Saved);

        book1Saved.setPublisher(publisherSaved);
        book2Saved.setPublisher(publisherSaved);

        //I added books,...but I did not persist them, so I am adding it now
        authorRepository.save(author1Saved);
        authorRepository.save(author2Saved);//without those 2 lines I would not hve persisted the book association
        bookRepository.save(book1Saved);
        bookRepository.save(book2Saved);

        System.out.println("in Bootstrap");
        System.out.println("Authors count: " + authorRepository.count());
        System.out.println("Books count: " + bookRepository.count());
        System.out.println("Publishers count: " + publisherRepository.count());
    }
}
