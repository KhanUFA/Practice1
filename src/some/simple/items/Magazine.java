package some.simple.items;

import some.Item;
import some.Shape;

public class Magazine extends Item {

    public Magazine(String name, int weight, int size, String color) {
        super(name, Shape.FLAT, weight, size, color);
    }
}
