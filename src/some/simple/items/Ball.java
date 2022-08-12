package some.simple.items;

import some.Item;
import some.Shape;

public class Ball extends Item {
    public Ball(String name, double weight, int size, String color) {
        super(name, Shape.ROUND, weight, size, color);
        /*width = size * 2;
        height = size * 2;*/
    }
}
