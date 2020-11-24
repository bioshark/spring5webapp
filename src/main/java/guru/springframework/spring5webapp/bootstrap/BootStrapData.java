package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher tor = new Publisher("Tor", "city center", "London", "Shire", "324SFN");
        publisherRepository.save(tor);

        Author asimov = new Author("Isaac", "Asimov");
        Book robots = new Book("The Complete Robots", "1223343");

        asimov.addBook(robots);
        robots.addAuthor(asimov);
        tor.addPublishedBook(robots);
        robots.setPublisher(tor);

        authorRepository.save(asimov);
        bookRepository.save(robots);
        publisherRepository.save(tor);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development", "334243");

        rod.addBook(noEJB);
        noEJB.addAuthor(rod);
        noEJB.setPublisher(tor);
        tor.addPublishedBook(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(tor);

        System.out.println("Bootstrap started");
        System.out.println("Authors: " + authorRepository.count());
        System.out.println("Books: " + bookRepository.count());
        System.out.println("Publishers: " + publisherRepository.count());
        System.out.println("Published books: " + tor.getBooks().size());

    }
}
