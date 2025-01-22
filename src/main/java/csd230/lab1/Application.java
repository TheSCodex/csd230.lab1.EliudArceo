package csd230.lab1;

import com.github.javafaker.Faker;
import csd230.lab1.entities.*;
import csd230.lab1.repositories.BookRepository;
import csd230.lab1.repositories.CartItemRepository;
import csd230.lab1.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	CartItemRepository cartItemRepository;

	@Autowired
	CartRepository cartRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			Cart cart = new Cart();
			cartRepository.save(cart);

			Faker faker = new Faker();
			com.github.javafaker.Book fakeBook = faker.book();
			com.github.javafaker.Number number = faker.number();
			com.github.javafaker.Code code = faker.code();

			for (int i = 0; i < 15; i++) {
				String title = fakeBook.title();
				double price = number.randomDouble(2, 10, 100);
				int copies = number.numberBetween(5, 20);
				int quantity = number.numberBetween(1, 50);
				String author = fakeBook.author();
				String isbn = code.isbn10();
				String description = "Book: " + title;

				Book book = new Book(
						price,
						quantity,
						description,
						title,
						copies,
						author,
						isbn
				);

				cart.addItem(book);
				repository.save(book);
				cartRepository.save(cart);
			}

			Iterable<Book> allBooks = repository.findAll();
			log.info("All books in the repository:");
			allBooks.forEach(book -> log.info(book.toString()));

			Book firstBook = allBooks.iterator().next();
			if (firstBook != null) {
				firstBook.setTitle("Updated Title: " + firstBook.getTitle());
				firstBook.setPrice(15.99);
				repository.save(firstBook);
				log.info("Updated book details: {}", firstBook);
			}

			log.info("Books with price > 50:");
			repository.findAll().forEach(book -> {
				if (book.getPrice() > 50) {
					log.info(book.toString());
				}
			});
		};
	}

}