package some.containers;

import some.Item;
import some.Shape;
import some.Storable;
import some.canvas.SVGWriter;
import some.exceptions.ItemAlreadyPlacedException;
import some.exceptions.StoringItemException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Bag extends Item implements Storable {
    private final List<Item> space;
    private final int maxWeight;

    public Bag(String name, double weight, int maxWeight) throws IllegalArgumentException {
        super(name, Shape.ROUND, weight, maxWeight, "grey");

        space = new ArrayList<>();
        this.maxWeight = maxWeight;
    }

    @Override
    public Item search(String name){
        for (Item item:
                space) {
            if(item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }

    @Override
    public void add(Item item) throws ItemAlreadyPlacedException, StoringItemException {
        if (item == null || item == this || item.isStored()  || item.getSize() > this.getSize() || !(item.getWeight() <= maxWeight - (getWeight() + super.getWeight()))) {
            checkStoringConditions(item);
        } else {
            item.setStored(true);
            space.add(item);
            System.out.println("Предмет " + item.getName() + " добавлен");
        }
    }

    @Override
    public boolean remove(Item item) {
        if(item != null){
            space.remove(item);
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
        return space.get(index);
    }

    @Override
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

        int widthContainer = getWidth();
        int heightContainer = getHeight();
        int posX = x + getWidth() / 2;
        int posY = heightContainer + y;
        int rx = widthContainer / 2, ry = heightContainer / 2;
        int x0 = x + rx, y0 = y + ry;

        svg.drawEllipse(x0, y0, rx, ry, this.getColor(),"grey");

        for (Item item: space) {

            while (Math.pow(posX - x0, 2) / Math.pow(rx, 2) + Math.pow(posY - y0, 2) / Math.pow(ry, 2) >= 1) {
                if (posX > x + widthContainer) {
                    posX = x;
                    posY -= item.getHeight() / 2;
                }
                posX += item.getWidth();
            }

            item.draw(svg, posX, posY - item.getHeight());
            posX += item.getWidth() + 2;
        }
    }

    @Override
    public int getWidth() {
        return super.getWidth() * 2;
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public void checkArguments(String name, Shape shape, double weight, int size, String color) throws IllegalArgumentException {
        Storable.super.checkArguments(name, shape, weight, size, color);
    }
}
