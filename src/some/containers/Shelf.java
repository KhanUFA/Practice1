package some.containers;

import some.Item;
import some.Shape;
import some.Storable;
import some.exceptions.ItemAlreadyPlacedException;
import some.exceptions.StoringItemException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class Shelf extends Item implements Storable {
    private final Deque<Item> space;
    private final double MAX_WEIGHT;

    public Shelf(String name, double weight, int maxSize, String color) throws IllegalArgumentException {
        super(name, Shape.FLAT, weight, maxSize, color);

        MAX_WEIGHT = maxSize;
        space = new ArrayDeque<>();
    }

    @Override
    public void add(Item item) throws ItemAlreadyPlacedException, StoringItemException {
        if(item != null && item != this && !item.isStored() && item.getWeight() <= MAX_WEIGHT - (getWeight() + super.getWeight())){
            if (space.size() != 0) {
                Item itemOnTop = space.peek();
                if (itemOnTop.getShape() == Shape.ROUND) {
                    throw new StoringItemException("Нельзя положить " + item.getName() + " на " + itemOnTop.getName());
                }
            }
            item.setStored(true);
            space.push(item);
            System.out.println("Предмет " + item.getName() + " добавлен");
        } else {
            checkStoringConditions(Objects.requireNonNull(item));
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
    public void checkArguments(String name, Shape shape, double weight, int size, String color) throws IllegalArgumentException {
        Storable.super.checkArguments(name, shape, weight, size, color);
    }
}
