package some;

public interface Storable {
    void add(Item item);
    boolean remove(Item item);
    Item get();
    double getWeight();
}
