package some;

import some.canvas.SVGWriter;

import java.io.IOException;

public abstract class Item {

    private String name, color;
    private final int size, width, height;
    private final double weight;
    private final Shape shape;
    private boolean stored;


    public Item(String name, Shape shape, double weight, int size, String color) throws IllegalArgumentException{
        checkArguments(name, shape, weight, size, color);

        this.name = name;
        this.shape = shape;
        this.weight = weight;
        this.size = size;
        width = size * 10;
        height = size * 10;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public void draw(SVGWriter svg, int x, int y) throws IOException {

        switch (this.shape){
            case RECTANGLE:
                svg.drawRect(x, y, getWidth(), getHeight(), this.color, "black");
                break;
            case FLAT:
                svg.drawRoundRect(x, y, getWidth(), getHeight(), this.color, "black");
                break;
            case ROUND:
                int radius = getWidth() / 2;
                svg.drawEllipse(x + radius, y + radius, radius, radius, this.color, "black");
                break;
        }
    }

    @Override
    public String toString(){
        return "Я " + name + " Являюсь: " + shape.toString() + " и я " + color
               + "\nМои параметры: " + weight + "кг, " + size + "см";
    }
}
