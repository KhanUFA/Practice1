package some.simple.items;

import some.Item;
import some.Shape;

public class Magazine extends Item {
    private final int height;

    public Magazine(String name, double weight, int size, String color) {
        super(name, Shape.FLAT, weight, size, color);
        height = size * 25 / 10;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
