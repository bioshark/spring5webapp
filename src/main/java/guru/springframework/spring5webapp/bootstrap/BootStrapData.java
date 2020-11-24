package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author asimov = new Author("Isaac", "Asimov");
        Book robots = new Book("The Complete Robots", "1223343");

        asimov.addBook(robots);
        robots.addAuthor(asimov);

        authorRepository.save(asimov);
        bookRepository.save(robots);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development", "334243");

        rod.addBook(noEJB);
        noEJB.addAuthor(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);


        System.out.println("Bootstrap started");
        System.out.println("Books: " + bookRepository.count());

    }
}
