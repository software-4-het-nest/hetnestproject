package be.hetnest.hetnestproject.dao;

import be.hetnest.hetnestproject.domain.Aanvraag;
import be.hetnest.hetnestproject.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AanvragenRepository extends JpaRepository<Aanvraag, Integer> {

}
