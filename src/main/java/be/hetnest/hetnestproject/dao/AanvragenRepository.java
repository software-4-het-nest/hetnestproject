package be.hetnest.hetnestproject.dao;

import be.hetnest.hetnestproject.domain.Aanvraag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AanvragenRepository extends JpaRepository<Aanvraag, Integer> {
}
