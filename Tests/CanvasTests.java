import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import some.Item;
import some.Shape;
import some.canvas.SVGWriter;
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
        Item ball = new Ball("Мяч", 10, 5, "green");
        Item brick = new Brick("Кирпич", 10, 5, "brown");
        Item magazine = new Magazine("Журнал", 1, 5, "grey");

        assertDoesNotThrow(() ->{
            svg.fillHead();

            brick.draw(svg, brick.getW(10) + 5,brick.getH(0) + 10 );
            ball.draw(svg, ball.getW(10) + 5, ball.getH(0) + 50);
            magazine.draw(svg, magazine.getW(10)+ 5, magazine.getH(0) + 100);

            svg.fillEndOfFile();
            Desktop.getDesktop().open(new File(fileName + ".svg"));
        });
    }

    @Test
    void simple () {
    }

}
