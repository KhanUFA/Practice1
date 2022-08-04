package some;

public interface Storable {
    boolean add(Item item);
    boolean remove(Item item);
    Item get();
}
