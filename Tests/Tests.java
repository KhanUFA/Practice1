import org.junit.jupiter.api.Test;
import some.Item;
import some.Storable;
import some.containers.Bag;
import some.containers.Box;
import some.containers.Shelf;
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
        System.out.println(ball);
    }

    @Test
    void checkContainerWeight(){
        Item ball = new Ball("Мяч", 5.5, 4, "green");
        Item bigBall = new Ball("Мячище", 5, 4, "green");
        Item MyBall = new Ball("Мячик", 5, 1, "green");
        Storable box = new Box("Яндекс.Доставка", 0.5, 10);

        Storable bagDelivery = new Bag("Деливери", 0.5, 10);

        try {
            box.add(ball);
            bagDelivery.add((Item) box);
            double bagDeliveryWeight = bagDelivery.getWeight();

            assertEquals(6., box.getWeight(), 0.0001);
            assertEquals(6.5, bagDeliveryWeight, 0.0001);

            assertThrows(StoringItemException.class, ()-> {
                bagDelivery.add(bigBall);
            }).printStackTrace();
        } catch (StoringItemException | ItemAlreadyPlacedException e) {
            e.printStackTrace();
        }
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
        } catch (StoringItemException | ItemAlreadyPlacedException e) {
            e.printStackTrace();
        }

        assertEquals(myBall, bag.search("Мячик"));
    }

    @Test
    void checkRemoveMethod(){
        Item ball = new Ball("Мяч", 5.5, 4, "green");
        Item magazine = new Magazine("The Rolling Stones", 0.225, 2, "green");
        Item brick = new Brick("Башкирский кирпич", 1.2, 5, "red");
        Storable box = new Box("Яндекс.Доставка", 0.5, 40);

        try {
            box.add(ball);
            box.add(magazine);
            box.add(brick);
        } catch (StoringItemException e) {
            e.printStackTrace();
        }

        assertTrue(box.remove(box.get()));
    }

    @Test
    void checkShelfLogic (){
        Item ball = new Ball("Мяч", 5.5, 4, "green");
        Item magazine = new Magazine("The Rolling Stones", 0.225, 2, "green");
        Item brick = new Brick("Башкирский кирпич", 1.2, 5, "red");
        Storable shelf = new Shelf("Shhonka",1,8,"grey");

        try {
            shelf.add(ball);
            assertThrows(StoringItemException.class, () -> {
               shelf.add(brick);
            });

            //A round object on another item. Now we put on the round object another object
            shelf.get();
            shelf.add(magazine);
            shelf.add(ball);
            assertThrows(StoringItemException.class, () -> {
                shelf.add(brick);
            });
        } catch (StoringItemException e) {
            e.printStackTrace();
        }
    }
}
