package shaurmas.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import shaurmas.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
