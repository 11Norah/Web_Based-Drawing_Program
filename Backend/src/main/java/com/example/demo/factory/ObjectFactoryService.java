package com.example.demo.factory;

import com.example.demo.response.*;
import com.example.demo.shapes.*;
import com.example.demo.shapes.Rectangle;
import org.springframework.stereotype.Component;

@Component
public class ObjectFactoryService implements ObjectFactoryI {


    public Shape getObject(String name,String color, Point first, Point second, Point third) {
        switch (name) {
            case "rectangle":
                return new Rectangle(first,second,color);
            case "square":
                return new Square(first,second,color);
            case "triangle":
                return new Triangle(first, second, third, color);
            case "ellipse":
                return new Ellipse(first,second,third,color);
            case "line":
                return new Line(first,second,color);
            case "circle":
                return new Circle(first,second,color);
            default:
                throw new NullPointerException();
        }
    }


}
