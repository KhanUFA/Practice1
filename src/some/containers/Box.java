package some.containers;

import some.Item;
import some.Shape;
import some.Storable;
import some.exceptions.ItemAlreadyPlacedException;
import some.exceptions.StoringItemException;

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
        if(item != null && item != this && !item.isStored() && item.getWeight() <= MAX_WEIGHT - (getWeight() + super.getWeight())){
            item.setStored(true);
            space.put(item.getName(), item);
            System.out.println("Предмет " + item.getName() + " добавлен");
        } else {
            checkStoringConditions(item);
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
    public void checkArguments(String name, Shape shape, double weight, int size, String color) throws IllegalArgumentException {
        Storable.super.checkArguments(name, shape, weight, size, color);
    }
}
