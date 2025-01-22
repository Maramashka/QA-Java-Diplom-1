package stellarburgers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static stellarburgers.Constants.DELTA;
import static stellarburgers.Constants.ERROR_MESSAGE;

@RunWith(Parameterized.class)
public class BunParamTest {

    private String name;
    private float price;
    private Bun bun;

    public BunParamTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Название булки: {0}, {1} монет")
    public static Object[][] getData() {
        return new Object[][]{
                {"Супербулка", 30.0f},
                {"Первый сорт", 20.0f},
                {"Второй сорт", 10.0f},
                {"Третий сорт не брак", 3.0f},
                {"", 0.0f},
                {null, 0.0f},
                {"СуперСуперСуперСуперСуперСуперСуперСуперСуперСуперСуперСуперСуперСуперСуперСуперСуперСуперСупер", 0.0f},
                {"$%&!", 0.0f},
                {"Супербулка", -30.0f},
                {"Супербулка", 0},
                {"Супербулка", 0.0000000000000000001f},
                {"Супербулка", 9999999999.999f},

        };
    }

    @Before
    public void initBun() {
        bun = new Bun(name, price);
    }


    @Test
    public void getNameBunTest() {

        String expected = name;
        String actual = bun.getName();

        assertEquals(ERROR_MESSAGE, expected, actual);
    }

    @Test
    public void getPriceBunTest() {

        float expected = price;
        float actual = bun.getPrice();

        assertEquals(ERROR_MESSAGE, expected, actual, DELTA);
    }
}