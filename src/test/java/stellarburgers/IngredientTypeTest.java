package stellarburgers;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static stellarburgers.Constants.ERROR_MESSAGE;

public class IngredientTypeTest extends Praktikum {

    SoftAssertions softAssertions = new SoftAssertions();

    private final String SAUCE = "SAUCE";
    private final String FILLING = "FILLING";

    @Test
    public void valuesCountEnumTest() {
        IngredientType[] sizes = IngredientType.values();

        assertEquals(ERROR_MESSAGE, 2, sizes.length);
    }

    @Test
    public void valuesEnumTest() {

        softAssertions.assertThat(IngredientType.SAUCE.toString())
                .as("Проверяем значение перечисления: соус")
                .isEqualTo(SAUCE);

        softAssertions.assertThat(IngredientType.FILLING.toString())
                .as("Проверяем значение перечисления: соус")
                .isEqualTo(FILLING);
    }
}