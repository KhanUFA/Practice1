import org.junit.jupiter.api.*;
import some.Item;
import some.Storable;
import some.containers.Bag;
import some.simple.items.*;
import static org.junit.jupiter.api.Assertions.*;

public class Tests{
    @Test
    void exceptionsTest(){
        assertThrows(IllegalArgumentException.class, () -> {
            Item ball = new Ball("Мяч", 5, 8, "green");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Item ball = new Ball(null, 5, 0, "green");
        });
    }

    @Test
    void checkToString(){
        Item ball = new Ball("Мяч", 5, 4, "green");
        String expectedString = "Я Мяч Являюсь: ROUND и я green\nМои параметры: 5.0кг, 4см";
        assertEquals(expectedString, ball.toString());
    }

    @Test
    void checkContainerWeight(){
        Item ball = new Ball("Мяч", 5.5, 4, "green");
        Item bigBall = new Ball("Мячище", 5, 4, "green");
        Item MyBall = new Ball("Мячик", 5, 1, "green");
        Storable bag = new Bag("Яндекс.Доставка", 0.5, 40);

        bag.add(ball);
        bag.add(bigBall);
        bag.add(MyBall);

        assertEquals(16., bag.getWeight());
        System.out.println(bag.get().getName());
    }
}
