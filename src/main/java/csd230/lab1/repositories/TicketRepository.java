package csd230.lab1.repositories;

import csd230.lab1.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Custom query to search by text
    List<Ticket> findByTextContaining(String text);

    // Custom query to search by text
    @Query("SELECT t FROM Ticket t WHERE t.text LIKE %:text%")
    List<Ticket> searchByText(@Param("text") String text);
}