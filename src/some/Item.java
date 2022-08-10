package some;

import some.canvas.SVGWriter;

import java.io.IOException;

public abstract class Item {

    private String name, color;
    private final int size;
    private final double weight;
    private final Shape shape;
    private boolean stored;

    public static final int MULTIPLIER_X = 4, MULTIPLIER_Y = 2;

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

    public void setStored(boolean stored) {
        this.stored = stored;
    }

    public void draw(SVGWriter svg, int x, int y) throws IOException {

        switch (this.shape){
            case RECTANGLE:
                svg.drawRect(x, y, getW(), getH(), this.color, "black");
                break;
            case FLAT:
                svg.drawRoundRect(x, y, getW(), getH(), this.color, "black");
                break;
            case ROUND:
                svg.drawEllipse(x, y, getW(), getW(), this.color, "black");
                break;
        }
    }

    public int getW() {
        return this.size * 10;

    }

    public int getH() {
        return this.size * 5;
    }

    @Override
    public String toString(){
        return "Я " + name + " Являюсь: " + shape.toString() + " и я " + color
               + "\nМои параметры: " + weight + "кг, " + size + "см";
    }
}
