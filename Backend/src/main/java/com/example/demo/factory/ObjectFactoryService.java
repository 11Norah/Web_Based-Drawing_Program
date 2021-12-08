package com.example.demo.factory;

import com.example.demo.response.*;
import com.example.demo.shapes.*;
import com.example.demo.shapes.Rectangle;
import org.springframework.stereotype.Component;

import java.awt.Point;

@Component
public class ObjectFactoryService implements ObjectFactoryI {


    @Override
    public ShapeI getObject(String name, Point first, Point second, Point third) {
        switch (name) {
            case "rectangle":
                return new Rectangle(first,second);
            case "square":
                return new Square(first,second);
            case "triangle":
                return new Triangle();
            case "ellipse":
                return new Ellipse(first,second,third);
            case "line":
                return new Line(first,second);
            case "circle":
                return new Circle(first,second);
            default:
                throw new NullPointerException();
        }
    }


}
