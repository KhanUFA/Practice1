package some.simple.items;

import some.Item;
import some.Shape;

public class Brick extends Item {

    public Brick(String name, int weight, int size, String color) {
        super(name, Shape.RECTANGLE, weight, size, color);
    }
}
