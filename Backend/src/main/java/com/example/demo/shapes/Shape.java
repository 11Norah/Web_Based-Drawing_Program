package com.example.demo.shapes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Rectangle.class, name = "rectangle"),
        @JsonSubTypes.Type(value = Circle.class, name = "circle"),
        @JsonSubTypes.Type(value = Square.class, name = "square"),
        @JsonSubTypes.Type(value = Triangle.class, name = "triangle"),
        @JsonSubTypes.Type(value = Ellipse.class, name = "ellipse"),
        @JsonSubTypes.Type(value = Line.class, name = "line")
})
public class Shape implements ShapeI{

    protected String color;
    protected ArrayList<Point> points;
    protected String name;


    public String getColor() {
        return color;
    }

    public String getName() {
        return this.name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void afterMove(Point click) {

    }

    @Override
    public boolean range(Point click) {
        return false;
    }

    @Override
    public Point[] getPoints() {
        return new Point[0];
    }

    @Override
    public void resize(Point p1, Point p2) {

    }


}
