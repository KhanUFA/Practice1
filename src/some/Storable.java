package some;

import some.exceptions.ItemAlreadyPlacedException;
import some.exceptions.StoringItemException;

public interface Storable {
    void add(Item item) throws ItemAlreadyPlacedException, StoringItemException;
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

    default void checkStoringConditions(Item item) throws StoringItemException, ItemAlreadyPlacedException {
        if(item.isStored()){
            throw new ItemAlreadyPlacedException("Предмет уже где-то лежит");
        }
        if(item == this){
            throw new StoringItemException("Нельзя положить предмет самого в себя");
        }
        throw new StoringItemException("Предмет не добавлен превышен максимальный вес");
    }
}
