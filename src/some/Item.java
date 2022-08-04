package some;

public abstract class Item {

    protected String name, color;
    protected final int size;
    protected final double weight;
    protected final Shape shape;
    protected boolean stored;

    public Item(String name, Shape shape, double weight, int size, String color) throws IllegalArgumentException{
        checkArguments(name, shape, weight, size, color);

        this.name = name;
        this.shape = shape;
        this.weight = weight;
        this.size = size;
        this.color = color;
        this.stored = false;
    }

    protected void checkArguments(String name, Shape shape, double weight, int size, String color) throws IllegalArgumentException{
        if(name == null){
            throw new IllegalArgumentException("Не задано имя объекта!");
        }
        if(shape == null){
            throw new IllegalArgumentException("Не задана форма объекта!");
        }
        if(weight < 0){
            throw new IllegalArgumentException("Вес не может быть отрицательным или нулем!");
        }
        if(size < 0){
            throw new IllegalArgumentException("Размер не может быть меньше или равен нулю!");
        } else if (size > 5){
            throw new IllegalArgumentException("Размер не может быть больше 5!");
        }
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

    protected void setStored(boolean stored) {
        this.stored = stored;
    }

    @Override
    public String toString(){
        return "Я " + name + " Являюсь: " + shape.toString() + " и я " + color
               + "\nМои параметры: " + weight + "кг, " + size + "см";
    }
}
