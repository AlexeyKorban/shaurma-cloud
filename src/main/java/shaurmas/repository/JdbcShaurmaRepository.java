package shaurmas.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import shaurmas.Ingredient;
import shaurmas.Shaurma;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcShaurmaRepository implements ShaurmaRepository {

    private JdbcTemplate jdbc;

    public JdbcShaurmaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Shaurma save(Shaurma shaurma) {
        long shaurmaId = saveShaurmaInfo(shaurma);
        shaurma.setId(shaurmaId);
        for (Ingredient ingredient : shaurma.getIngredients()) {
            saveIngredientToShaurma(ingredient, shaurmaId);
        }
        return shaurma;
    }

    private long saveShaurmaInfo(Shaurma shaurma) {
        shaurma.setCreatedAt(new Date());
        PreparedStatementCreator psc =
                new PreparedStatementCreatorFactory(
                        "insert into Shaurma (name, createdAt) values (?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP
                ).newPreparedStatementCreator(
                        Arrays.asList(
                                shaurma.getName(),
                                new Timestamp(shaurma.getCreatedAt().getTime())));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToShaurma(Ingredient ingredient, long shaurmaId) {
        jdbc.update("INSERT INTO Shaurma_Ingredients (shaurma, ingredient) "
        + "values (?, ?)",
                shaurmaId, ingredient.getId());
    }
}
