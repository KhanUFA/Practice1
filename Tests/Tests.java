import org.junit.jupiter.api.*;
import some.Item;
import some.Storable;
import some.containers.Bag;
import some.exceptions.ItemAlreadyPlacedException;
import some.exceptions.StoringItemException;
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
        }).printStackTrace();

        assertThrows(StoringItemException.class, () -> {
            Storable bag = new Bag("Яндекс.Доставка", 0.5, 10);
            bag.add((Item) bag);
        });
        assertThrows(StoringItemException.class, () -> {
            Item ball = new Ball("Баскетбольный мяч", 5, 1, "green");
            Bag bag = new Bag("Яндекс.Доставка", 0.5, 5);
            bag.add(ball);
        });

        assertThrows(ItemAlreadyPlacedException.class, () -> {
            Item ball = new Ball("Баскетбольный мяч", 5, 1, "green");
            Bag bag = new Bag("Яндекс.Доставка", 0.5, 10);
            bag.add(ball);
            bag.add(ball);
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
        Storable bag = new Bag("Яндекс.Доставка", 0.5, 10);

        Storable bagDelivery = new Bag("Деливери", 0.5, 10);

        try {
            bag.add(ball);
            bagDelivery.add((Item) bag);
        } catch (StoringItemException e) {
            e.printStackTrace();
        }

        double bagDeliveryWeight = bagDelivery.getWeight();
        assertEquals(6., bag.getWeight());
        assertEquals(6.5, bagDeliveryWeight);

        System.out.println(bagDeliveryWeight);
    }

    @Test
    void checkSearchMethod(){
        Item ball = new Ball("Мяч", 5.5, 4, "green");
        Item bigBall = new Ball("Мячище", 5, 4, "green");
        Item myBall = new Ball("Мячик", 5, 1, "green");
        Storable bag = new Bag("Яндекс.Доставка", 0.5, 40);

        try {
            bag.add(ball);
            bag.add(bigBall);
            bag.add(myBall);
        } catch (StoringItemException e) {
            e.printStackTrace();
        }

        assertEquals(myBall, bag.search("Мячик"));
    }

    @Test
    void checkRemoveMethod(){
        Item ball = new Ball("Мяч", 5.5, 4, "green");
        Storable bag = new Bag("Яндекс.Доставка", 0.5, 40);
    }
}
