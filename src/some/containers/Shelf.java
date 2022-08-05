package some.containers;

import some.Item;
import some.Shape;
import some.Storable;

import java.util.ArrayDeque;
import java.util.Deque;

public class Shelf extends Item implements Storable {
    private final Deque<Item> space;
    private final double MAX_WEIGHT;

    public Shelf(String name, Shape shape, double weight, int maxSize, String color) throws IllegalArgumentException {
        super(name, shape, weight, maxSize, color);

        MAX_WEIGHT = maxSize;
        space = new ArrayDeque<Item>();
    }

    @Override
    public boolean add(Item item) {
        if(item != null && !item.isStored() && getWeight() + item.getWeight() < MAX_WEIGHT){
            if(item.getShape() != Shape.ROUND){
            item.setStored(true);
            space.push(item);
            System.out.println("Предмет добавлен");
            return true;
            } else {
                Item itemOnTop = space.peek();
                if (itemOnTop.getShape() == Shape.ROUND) {
                    System.out.println("Нельзя положить " + item.getName() + " .Мешает " + itemOnTop.getName());
                    return false;
                }
            }
        } else {
            if(item.isStored()){
                System.out.println("Предмет уже где-то лежит");
                return false;
            }
            System.out.println("Предмет не добавлен превышен максимальный вес");
            return false;
        }
        return false;
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
