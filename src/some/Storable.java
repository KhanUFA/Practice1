package some;

public interface Storable {
    boolean add(Item item);
    boolean remove(Item item);
    Item get();
    Item search(String name);
    double getWeight();

    default void checkArguments(String name, Shape shape, double weight, int size, String color) throws IllegalArgumentException {
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
}
