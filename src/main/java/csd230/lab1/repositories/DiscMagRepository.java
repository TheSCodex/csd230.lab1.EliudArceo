package csd230.lab1.repositories;

import csd230.lab1.entities.DiscMag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiscMagRepository extends JpaRepository<DiscMag, Long> {

    // Custom query to search by text in the title
    List<DiscMag> findByTitleContaining(String text);

    // Custom query to search by price
    List<DiscMag> findByPrice(double price);

    // Custom query to search by quantity
    List<DiscMag> findByQuantity(int quantity);

    // Custom query to search by price greater than a specific value
    List<DiscMag> findByPriceGreaterThan(double price);

    // Custom query to search by quantity less than a specific value
    List<DiscMag> findByQuantityLessThan(int quantity);

    // Custom query to search by title and price
    List<DiscMag> findByTitleAndPrice(String title, double price);

    // Custom query to search by title
    @Query("SELECT d FROM DiscMag d WHERE d.title LIKE %:text%")
    List<DiscMag> searchByTitle(@Param("text") String text);
}