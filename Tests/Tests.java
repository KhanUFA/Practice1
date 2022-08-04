import org.junit.jupiter.api.*;
import some.Item;
import some.Storable;
import some.containers.Box;
import some.simple.items.*;
import static org.junit.jupiter.api.Assertions.*;

public class Tests{
    @Test
    void createTest(){
        Item ball = new Ball("Мяч", 5, 4, "green");
        assertEquals("Мяч", ball.getName());
        System.out.println(ball);
    }

    @Test
    void checkToString(){
        Item ball = new Ball("Мяч", 5, 4, "green");
        String expectedString = "Я Мяч Являюсь: ROUND и я green\nМои параметры: 5.0кг, 4см";
        assertEquals(expectedString, ball.toString());
    }

    @Test
    void addToContainer(){
        Item ball = new Ball("Мяч", 5, 4, "green");
        Item bigBall = new Ball("Мячище", 5, 10, "green");
        Item MyBall = new Ball("Мячик", 5, 1, "green");
        Storable box = new Box("Яндекс.Доставка", 0.5, 40);

        box.add(ball);
        box.add(bigBall);
        box.add(MyBall);
        assertNotNull(box.get());

        System.out.println(box.get().getName());
    }
}
