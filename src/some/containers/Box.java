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
    private final int maxWeight;

    public Box(String name, double weight, int maxWeight) throws IllegalArgumentException {
        super(name, Shape.RECTANGLE, weight, maxWeight, "brown");
        space = new HashMap<>();
        this.maxWeight = maxWeight;
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
        if (item == null || item == this || item.isStored() || item.getSize() > this.getSize() || !(item.getWeight() <= maxWeight - (getWeight() + super.getWeight()))) {
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

        int widthContainer = getWidth(), heightContainer = getHeight();
        int posX = x;
        int posY = widthContainer + y;

        svg.drawRect(x, y, widthContainer, heightContainer, this.getColor(),"grey");

        for (Item item: space.values()) {

            if(posX + item.getWidth() >= x + widthContainer - 5){
                posX = x;
                posY -= 40;
            }

            item.draw(svg, posX, posY - item.getHeight() - 2);

            posX += item.getWidth() + 2;
        }
    }

    @Override
    public void checkArguments(String name, Shape shape, double weight, int size, String color) throws IllegalArgumentException {
        Storable.super.checkArguments(name, shape, weight, size, color);
    }


}
