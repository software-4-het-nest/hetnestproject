package be.hetnest.hetnestproject.dao;

import be.hetnest.hetnestproject.domain.Aanbieding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AanbiedingRepository extends JpaRepository<Aanbieding, Integer>{
    Aanbieding findById(long id);
    List<Aanbieding> findAllByStatus(String status);
    Aanbieding findFirstByOrderByIdDesc();
    Aanbieding findByNaam(String naam);
    void deleteById(long id);
}