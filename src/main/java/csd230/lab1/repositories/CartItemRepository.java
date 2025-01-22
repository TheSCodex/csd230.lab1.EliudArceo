package csd230.lab1.repositories;

import csd230.lab1.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Custom query to search by text in the description
    List<CartItem> findByDescriptionContaining(String text);

    // Custom query to search by price
    List<CartItem> findByPrice(double price);

    // Custom query to search by quantity
    List<CartItem> findByQuantity(int quantity);

    // Custom query to search by price greater than a specific value
    List<CartItem> findByPriceGreaterThan(double price);

    // Custom query to search by quantity less than a specific value
    List<CartItem> findByQuantityLessThan(int quantity);

    // Custom query to search by description and price
    List<CartItem> findByDescriptionAndPrice(String description, double price);

    // Custom query to search by description using @Query decorator
    @Query("SELECT c FROM CartItem c WHERE c.description LIKE %:text%")
    List<CartItem> searchByDescription(@Param("text") String text);

    // Custom query to search by price range using @Query decorator
    @Query("SELECT c FROM CartItem c WHERE c.price BETWEEN :minPrice AND :maxPrice")
    List<CartItem> findByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    // Custom query to search by quantity range using @Query decorator
    @Query("SELECT c FROM CartItem c WHERE c.quantity BETWEEN :minQuantity AND :maxQuantity")
    List<CartItem> findByQuantityRange(@Param("minQuantity") int minQuantity, @Param("maxQuantity") int maxQuantity);
}