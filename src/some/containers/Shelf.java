package some.containers;

import some.Item;
import some.Shape;
import some.Storable;
import some.exceptions.ItemAlreadyPlacedException;
import some.exceptions.StoringItemException;

import java.util.ArrayDeque;
import java.util.Deque;

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
            if(item.getShape() != Shape.ROUND){
            item.setStored(true);
            space.push(item);
            System.out.println("Предмет добавлен");
            } else {
                Item itemOnTop = space.peek();
                if (itemOnTop.getShape() == Shape.ROUND) {
                    System.out.println("Нельзя положить " + item.getName() + " .Мешает " + itemOnTop.getName());
                }
            }
        } else {
            checkStoringConditions(item);
        }
    }

    @Override
    public boolean remove(Item item) {
        if(item != null){
            space.pop();
            item.setStored(false);
            return true;
        }
        return false;
    }

    @Override
    public Item get() {
        return space.peek();
    }

    @Override
    public Item search(String name) {
        return space.peek();
    }

    @Override
    public void checkArguments(String name, Shape shape, double weight, int size, String color) throws IllegalArgumentException {
        Storable.super.checkArguments(name, shape, weight, size, color);
    }
}
