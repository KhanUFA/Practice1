package some.containers;

import some.Item;
import some.Shape;
import some.Storable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag extends Item implements Storable {
    private final List<Item> space;
    private final int MAX_WEIGHT;

    public Bag(String name, double weight, int maxSize) throws IllegalArgumentException {
        super(name, Shape.RECTANGLE, weight, maxSize, "brown");

        space = new ArrayList<>();
        MAX_WEIGHT = maxSize;
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
    public boolean add(Item item) {
        if(item != null && !item.isStored() && getWeight() < MAX_WEIGHT){
            item.setStored(true);
            space.add(item);
            System.out.println("Предмет добавлен");
            return true;
        } else {
            if(item.isStored()){
                System.out.println("Предмет уже где-то лежит");
                return false;
            }
            System.out.println("Предмет не добавлен превышен максимальный вес");
            return false;
        }
    }

    @Override
    public boolean remove(Item item) {
        if(item != null){
            space.remove(item);
            item.setStored(false);
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
