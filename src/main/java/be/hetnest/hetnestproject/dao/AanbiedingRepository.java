package be.hetnest.hetnestproject.dao;

import be.hetnest.hetnestproject.domain.Aanbieding;
import org.springframework.data.repository.CrudRepository;

public interface AanbiedingRepository extends CrudRepository<Aanbieding, Long>{
    public Aanbieding findById(long id);
    public Aanbieding findFirstByOrderByIdDesc();
}