package some.containers;

import some.Item;
import some.Shape;
import some.Storable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Box extends Item implements Storable {
    private List<Item> space;

    public Box(String name, double weight, int size) throws IllegalArgumentException {
        super(name, Shape.RECTANGLE, weight, size, "brown");

        space = new ArrayList<>();
    }

    @Override
    public boolean add(Item item) {
        if(item != null){
            space.add(item);
            return true;
        }
        return false;
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
}
