import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import some.Item;
import some.Shape;
import some.canvas.SVGWriter;
import some.containers.Bag;
import some.containers.Box;
import some.simple.items.Ball;
import some.simple.items.Brick;
import some.simple.items.Magazine;

import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CanvasTests {
    @Test
    void simpleDraw () {
        String fileName = "simpleTest";
        SVGWriter svg = new SVGWriter(fileName);
        Item ball = new Ball("Мяч", 0.5, 5, "green");
        Item brick = new Brick("Кирпич", 0.5, 5, "brown");
        Item magazine = new Magazine("Журнал", 1, 5, "grey");
        Box box = new Box("Яндекс.Доставка", 0.5, 10);

        assertDoesNotThrow(() ->{
            svg.fillHead();

            /*brick.draw(svg, brick.getW() + 5,brick.getH() + 10 );
            ball.draw(svg, ball.getW() + 5, ball.getH() + 50);
            magazine.draw(svg, magazine.getW()+ 5, magazine.getH() + 100);*/

            box.add(magazine);
            box.add(brick);
            box.add(ball);
            //box.add(bag);

            box.draw(svg, 20, 100);
            svg.fillEndOfFile();
        });
    }

    @Test
    void simple () {
    }

}
