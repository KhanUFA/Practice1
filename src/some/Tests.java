package some;

import org.junit.jupiter.api.*;
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
}
