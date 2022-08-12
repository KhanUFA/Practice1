import org.junit.jupiter.api.Test;
import some.Item;
import some.canvas.SVGWriter;
import some.containers.Bag;
import some.containers.Box;
import some.containers.Shelf;
import some.simple.items.Ball;
import some.simple.items.Brick;
import some.simple.items.Magazine;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CanvasTests {
    @Test
    void simpleDraw () {
        String fileName = "simpleTest";
        SVGWriter svg = new SVGWriter(fileName);
        Item ball = new Ball("Мяч", 0.5, 2, "green");
        Item brick = new Brick("Кирпич", 2, 5, "brown");
        Item magazine = new Magazine("Журнал", 1, 5, "grey");
        Item ball1 = new Ball("Мяч", 0.5, 2, "white");
        Item brick1 = new Brick("Кирпич", 2, 2, "pink");
        Item magazine1 = new Magazine("Журнал", 1, 1, "black");
        Box box = new Box("Яндекс.Доставка", 0.5, 20);
        Bag bag = new Bag("Delivery", 0.5, 10);
        Shelf shelf = new Shelf("RAJTAN", 1,10,"#CD5C5C");

        assertDoesNotThrow(() ->{
            svg.fillHead();

            bag.add(magazine1);
            bag.add(brick1);
            bag.add(ball1);

            box.add(bag);

            box.add(ball);
            box.add(magazine);
            box.add(brick);

            box.draw(svg, 20, 150);
            bag.draw(svg, 250, 250);

            bag.remove(brick1);

            box.remove(ball);
            box.remove(magazine);

            shelf.add(magazine);
            shelf.add(brick1);
            shelf.add(ball);

            shelf.draw(svg, 20, 550);

            svg.fillEndOfFile();
        });
    }
}
