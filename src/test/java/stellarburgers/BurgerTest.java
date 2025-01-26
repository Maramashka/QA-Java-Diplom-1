package stellarburgers;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;
    private SoftAssertions softAssertions;

    @Mock
    Bun bunMock;

    @Mock
    Ingredient ingredientMock;

    @Mock
    Ingredient ingredientMock1;

    @Mock
    Ingredient ingredientMock2;

    @Before
    public void initBurgerAndAssertions() {
        burger = new Burger();
        softAssertions = new SoftAssertions();
    }

    @Test
    public void setBunsTest() {

        when(bunMock.getName()).thenReturn("Супербулка");
        when(bunMock.getPrice()).thenReturn(30.0f);

        // передаём мок булки в бургер
        burger.setBuns(bunMock);

        softAssertions.assertThat(burger.bun.getName()).as("Проверяем название булки")
                .isEqualTo("Супербулка");
        softAssertions.assertThat(burger.bun.getPrice()).as("Проверяем цену булки")
                .isEqualTo(30.0f);
        softAssertions.assertAll();
    }

    @Test
    public void addIngredientTest() {

        burger.ingredients.clear();
        burger.addIngredient(ingredientMock);
        softAssertions.assertThat(burger.ingredients.contains(ingredientMock))
                .as("Проверяем + 1 ингредиент")
                .isTrue();

        burger.ingredients.clear();
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock1);

        softAssertions.assertThat(burger.ingredients.size())
                .as("Проверяем + 2 ингредиента")
                .isEqualTo(2);

        burger.ingredients.clear();
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        softAssertions.assertThat(burger.ingredients.size())
                .as("Проверяем + 3 ингредиента")
                .isEqualTo(3);
    }

    @Test
    public void removeIngredientTest() {
        burger.ingredients.clear();

        softAssertions.assertThat(burger.ingredients.isEmpty())
                .as("Проверяем, что список ингредиентов пуст")
                .isTrue();

        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.removeIngredient(0);
        burger.removeIngredient(1);

        softAssertions.assertThat(burger.ingredients.contains(ingredientMock))
                .as("Проверяем удаление 1-го элемента списка")
                .isFalse();

        softAssertions.assertThat(burger.ingredients.contains(ingredientMock1))
                .as("Проверяем удаление 3-го элемента списка")
                .isFalse();
    }

    @Test
    public void moveIngredientTest() {
        burger.ingredients.clear();

        softAssertions.assertThat(burger.ingredients.isEmpty())
                .as("Проверяем, что список ингредиентов пуст")
                .isTrue();

        burger.ingredients.add(0, ingredientMock);
        burger.ingredients.set(0, ingredientMock2);

        softAssertions.assertThat(burger.ingredients.size())
                .as("Проверяем количество ингредиентов")
                .isEqualTo(1);

        softAssertions.assertThat(burger.ingredients.contains(ingredientMock))
                .as("Проверяем, остался ли в бургере удалённый ингредиент")
                .isFalse();

        softAssertions.assertThat(burger.ingredients.get(0))
                .as("Проверяем, что новый ингредиент добавился на место удалённого ингредиента")
                .isEqualTo(0);
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(bunMock);

        burger.ingredients.add(ingredientMock);
        burger.ingredients.add(ingredientMock1);
        burger.ingredients.add(ingredientMock2);

        when(bunMock.getPrice()).thenReturn(30.0f);
        when(ingredientMock.getPrice()).thenReturn(5.0f);
        when(ingredientMock1.getPrice()).thenReturn(10.0f);
        when(ingredientMock2.getPrice()).thenReturn(15.0f);

        float expected = 90.0f;
        float actual = burger.getPrice();

        Assert.assertEquals("Метод сломался, почините его", expected, actual, 0.5);
    }

    @Test
    public void getReceiptTest() {
        burger.setBuns(bunMock);

        when(bunMock.getName()).thenReturn("Флюоресцентная булка R2-D3");

        when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock.getName()).thenReturn("сыр с астероидной плесенью");

        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getName()).thenReturn("соус с шипами антарианского плоскоходца");

        burger.ingredients.add(ingredientMock);
        burger.ingredients.add(ingredientMock1);

        String expected = "(==== флюоресцентная булка R2-D3 ====)\n" +
                "= filling сыр с астероидной плесенью =\n" +
                "= sauce соус с шипами антарианского плоскоходца =\n" +
                "==== флюоресцентная булка R2-D3 ====\n" +
                "\n" +
                "Price: %.6f\n";

        Assert.assertEquals("Чек не совпадает", expected, burger.getReceipt());
    }
}