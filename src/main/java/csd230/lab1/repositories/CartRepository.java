package csd230.lab1.repositories;

import csd230.lab1.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // Custom query to search by Id
    Cart findById(long id);
}