package some.containers;

import some.Item;
import some.Shape;
import some.Storable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag extends Item implements Storable {
    private List<Item> space;

    public Bag(String name, double weight, int size) throws IllegalArgumentException {
        super(name, Shape.RECTANGLE, weight, size, "brown");

        space = new ArrayList<>();
    }

    @Override
    protected void checkArguments(String name, Shape shape, double weight, int size, String color) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Не задано имя объекта!");
        }
        if (shape == null) {
            throw new IllegalArgumentException("Не задана форма объекта!");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Вес не может быть отрицательным или нулем!");
        }
        if (size < 0) {
            throw new IllegalArgumentException("Размер не может быть меньше или равен нулю!");
        }
    }

    @Override
    public void add(Item item) {
        if(item != null){
            space.add(item);
        }
    }

    @Override
    public boolean remove(Item item) {
        if(item != null){
            space.add(item);
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

    Item search(String name){
        for (Item item:
             space) {
            if(item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }

    @Override
    public double getWeight() {
        double sumWeight = weight;

        if(space.size() == 0){
            return sumWeight;
        }

        for (Item item:
             space) {
            sumWeight += item.getWeight();
        }
        return sumWeight;
    }
}
