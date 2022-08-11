import org.junit.jupiter.api.Test;
import some.Item;
import some.canvas.SVGWriter;
import some.containers.Bag;
import some.containers.Box;
import some.containers.Shelf;
import some.simple.items.Ball;
import some.simple.items.Brick;
import some.simple.items.Magazine;

import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CanvasTests {
    @Test
    void simpleDraw () {
        String fileName = "simpleTest";
        SVGWriter svg = new SVGWriter(fileName);
        Item ball = new Ball("Мяч", 0.5, 2, "green");
        Item brick = new Brick("Кирпич", 2, 5, "brown");
        Item magazine = new Magazine("Журнал", 1, 5, "grey");
        Item ball1 = new Ball("Мяч", 0.5, 4, "white");
        Item brick1 = new Brick("Кирпич", 2, 5, "pink");
        Item magazine1 = new Magazine("Журнал", 1, 5, "black");
        Box box = new Box("Яндекс.Доставка", 0.5, 20);
        Bag bag = new Bag("Delivery", 0.5, 28);
        Shelf shelf = new Shelf("RAJTAN", 1,10,"#CD5C5C");

        assertDoesNotThrow(() ->{
            svg.fillHead();

            box.add(ball);
            box.add(magazine);
            box.add(brick);

            bag.add(magazine1);
            bag.add(brick1);
            bag.add(ball1);

            //box.add(bag);
            box.draw(svg, 20, 150);
            bag.draw(svg, 200, 250);

            bag.remove(magazine1);
            bag.remove(brick1);
            bag.remove(ball1);

            box.remove(ball);
            shelf.add(magazine1);
            shelf.add(brick1);
            shelf.add(ball);

            shelf.draw(svg, 20, 550);

            svg.fillEndOfFile();
        });
    }



}
