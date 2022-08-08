package some.containers;

import some.Item;
import some.Shape;
import some.Storable;
import some.exceptions.ItemAlreadyPlacedException;
import some.exceptions.StoringItemException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag extends Item implements Storable {
    private final List<Item> space;
    private final int maxWeight;

    public Bag(String name, double weight, int maxWeight) throws IllegalArgumentException {
        super(name, Shape.RECTANGLE, weight, maxWeight, "brown");

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
        if (item == null || item == this || item.isStored() || !(item.getWeight() <= maxWeight - (getWeight() + super.getWeight()))) {
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
    public void checkArguments(String name, Shape shape, double weight, int size, String color) throws IllegalArgumentException {
        Storable.super.checkArguments(name, shape, weight, size, color);
    }
}
