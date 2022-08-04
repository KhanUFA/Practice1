package some;

public abstract class Item {

    private String name, color;
    private final int size;
    private final double weight;
    private final Shape shape;
    private boolean stored;

    public Item(String name, Shape shape, double weight, int size, String color) throws IllegalArgumentException{
        //Проверка Стринг и Shape на null + Максимальный вес и размер
        if(weight < 0 && size < 0){
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.shape = shape;
        this.weight = weight;
        this.size = size;
        this.color = color;
        this.stored = false;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public double getWeight() {
        return weight;
    }

    public int getSize() {
        return size;
    }

    public Shape getShape() {
        return shape;
    }

    public boolean isStored() {
        return stored;
    }

    public void setStored(boolean stored) {
        this.stored = stored;
    }

    @Override
    public String toString(){
        return "Я " + name + " Являюсь: " + shape.toString() + " и я " + color
               + "\nМои параметры: " + weight + "кг, " + size + "см";
    }
}
