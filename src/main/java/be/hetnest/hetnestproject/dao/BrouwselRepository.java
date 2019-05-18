package be.hetnest.hetnestproject.dao;


import be.hetnest.hetnestproject.domain.Brouwsel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BrouwselRepository extends JpaRepository<Brouwsel, Integer>{
    Brouwsel findById(long id);
    Brouwsel findByNaam(String naam);
    Brouwsel findBynaamExterneBrouwer(String externeBrouwer);

    void deleteById(long id);
}
