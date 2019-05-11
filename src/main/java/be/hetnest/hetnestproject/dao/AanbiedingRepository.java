package be.hetnest.hetnestproject.dao;

import be.hetnest.hetnestproject.domain.Aanbieding;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AanbiedingRepository extends JpaRepository<Aanbieding, Integer>{
    public Aanbieding findById(long id);
    public Aanbieding findFirstByOrderByIdDesc();
    Aanbieding findByNaam(String naam);
    void deleteById(long id);
}