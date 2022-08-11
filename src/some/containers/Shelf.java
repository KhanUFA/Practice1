package some.containers;

import some.Item;
import some.Shape;
import some.Storable;
import some.canvas.SVGWriter;
import some.exceptions.ItemAlreadyPlacedException;
import some.exceptions.StoringItemException;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Objects;

public class Shelf extends Item implements Storable {
    private final ArrayDeque<Item> space;
    private final double maxWeight;

    public Shelf(String name, double weight, int maxWeight, String color) throws IllegalArgumentException {
        super(name, Shape.FLAT, weight, maxWeight, color);

        this.maxWeight = maxWeight;
        space = new ArrayDeque<>();
    }

    @Override
    public void add(Item item) throws ItemAlreadyPlacedException, StoringItemException {
        if (item == null || item == this || item.isStored() || item.getSize() > this.getSize() || !(item.getWeight() <= maxWeight - (getWeight() + super.getWeight()))) {
            checkStoringConditions(Objects.requireNonNull(item));
        } else {
            if (space.size() != 0) {
                Item itemOnTop = space.peek();
                if (itemOnTop.getShape() == Shape.ROUND) {
                    throw new StoringItemException("Нельзя положить " + item.getName() + " на " + itemOnTop.getName());
                }
            }

            item.setStored(true);
            space.push(item);
            System.out.println("Предмет " + item.getName() + " добавлен");
        }
    }

    @Override
    public boolean remove(Item item) {
        System.out.println("Я ничего не делаю PepeChill");
        return false;
    }

    @Override
    public Item get() {
        Item item = space.pop();
        item.setStored(false);
        System.out.println("Предмет " + item.getName() + " убран");
        return item;
    }

    @Override
    public Item search(String name) {
        for (Item item:
                space) {
            if(item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }

    public double getWeight() {
        double sumWeight = super.getWeight();

        for (Item item:
                space) {
            sumWeight += item.getWeight();
        }

        return sumWeight;
    }

    @Override
    public void draw(SVGWriter svg, int x, int y) throws IOException {

        ArrayDeque<Item> tempSpace = space.clone();
        int widthContainer = getWidth(), heightContainer = getHeight();
        int posY = y - 1, posX = x;

        svg.drawRect(x, y, widthContainer, heightContainer, this.getColor(),"grey");

        while (!tempSpace.isEmpty()){
            Item item = tempSpace.pollLast();

            posY -= item.getHeight() - 1;
            posX = (x + widthContainer / 2) - (item.getWidth() / 2);

            item.draw(svg, posX, posY);
        }
    }

    @Override
    public int getHeight() {
        return getSize() * 2;
    }

    @Override
    public void checkArguments(String name, Shape shape, double weight, int size, String color) throws IllegalArgumentException {
        Storable.super.checkArguments(name, shape, weight, size, color);
    }
}
