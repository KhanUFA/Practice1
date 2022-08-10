import org.junit.jupiter.api.Test;
import some.Item;
import some.canvas.SVGWriter;
import some.containers.Bag;
import some.containers.Box;
import some.simple.items.Ball;
import some.simple.items.Brick;
import some.simple.items.Magazine;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CanvasTests {
    @Test
    void simpleDraw () {
        String fileName = "simpleTest";
        SVGWriter svg = new SVGWriter(fileName);
        Item ball = new Ball("Мяч", 0.5, 5, "green");
        Item brick = new Brick("Кирпич", 2, 5, "brown");
        Item magazine = new Magazine("Журнал", 1, 5, "grey");
        Item ball1 = new Ball("Мяч", 0.5, 5, "green");
        Item brick1 = new Brick("Кирпич", 2, 5, "brown");
        Item magazine1 = new Magazine("Журнал", 1, 5, "grey");
        Box box = new Box("Яндекс.Доставка", 0.5, 20);
        Bag bag = new Bag("Delivery", 0.5, 10);

        assertDoesNotThrow(() ->{
            svg.fillHead();

            box.add(magazine);
            box.add(brick);
            box.add(ball);

            bag.add(magazine1);
            bag.add(brick1);
            bag.add(ball1);

            box.add(bag);
            box.draw(svg, 20, 100);

            svg.fillEndOfFile();
        });
    }



}
