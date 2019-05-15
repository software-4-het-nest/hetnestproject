package be.hetnest.hetnestproject.dao;

import be.hetnest.hetnestproject.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientenRepository extends JpaRepository<Ingredient, Integer> {
    Ingredient findById(long id);
    List<Ingredient> findAllByStatus(String status);
}
