package some.containers;

import some.Item;
import some.Shape;
import some.Storable;
import some.canvas.SVGWriter;
import some.exceptions.ItemAlreadyPlacedException;
import some.exceptions.StoringItemException;

import java.io.IOException;
import java.util.*;

public class Box extends Item implements Storable {

    private final Map<String, Item> space;
    private final int MAX_WEIGHT;

    public Box(String name, double weight, int maxSize) throws IllegalArgumentException {
        super(name, Shape.RECTANGLE, weight, maxSize, "brown");
        space = new HashMap<>();
        MAX_WEIGHT = maxSize;
    }

    @Override
    public Item search(String name){
        if(!space.isEmpty()) {
            return space.get(name);
        }
        return null;
    }

    @Override
    public void add(Item item) throws ItemAlreadyPlacedException, StoringItemException {
        if (item == null || item == this || item.isStored() || !(item.getWeight() <= MAX_WEIGHT - (getWeight() + super.getWeight()))) {
            checkStoringConditions(item);
        } else {
            item.setStored(true);
            space.put(item.getName(), item);
            System.out.println("Предмет " + item.getName() + " добавлен");
        }
    }

    @Override
    public boolean remove(Item item) {
        if(item != null){
            space.remove(item.getName());
            item.setStored(false);
            System.out.println("Предмет " + item.getName() + " убран");
            return true;
        }
        return false;
    }

    @Override
    public Item get() {
        Random random = new Random(10);
        int index = random.nextInt(space.size());

        return space.get(space.keySet().toArray()[index]);
    }

    @Override
    public double getWeight() {
        double sumWeight = super.getWeight();

        for (Item item:
                space.values()) {
            sumWeight += item.getWeight();
        }

        return sumWeight;
    }

    @Override
    public void draw(SVGWriter svg, int x, int y) throws IOException {

        int widthContainer = getW();
        int posX = x;
        int posY = widthContainer + y;

        svg.drawRect(x, y, widthContainer, widthContainer, this.getColor(),"grey");

        for (Item item: space.values()) {

            if(posX >= x + widthContainer - 1){
                posX = x;
                posY -= item.getH();
            }

            if(item.getShape() == Shape.ROUND){
                posX += item.getW() + 2;
            }

            item.draw(svg, posX, posY - item.getW());

            posX = posX + item.getW();
        }
    }

    @Override
    public void checkArguments(String name, Shape shape, double weight, int size, String color) throws IllegalArgumentException {
        Storable.super.checkArguments(name, shape, weight, size, color);
    }


}
