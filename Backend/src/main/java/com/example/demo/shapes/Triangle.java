package com.example.demo.shapes;

import com.example.demo.services.LineServices;
import org.springframework.beans.factory.annotation.Autowired;

public class Triangle extends Shape implements ShapeI {
    private Point first, second, third;

    public Triangle(Point first, Point second, Point third, String color) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.color = color;
        this.name = "triangle";
    }

    @Autowired
    LineServices lineServices;

    public boolean range(com.example.demo.shapes.Point click) {
        return lineServices.checkPoint(first, second, click) || lineServices.checkPoint(first, third, click) || lineServices.checkPoint(second, third, click);
    }


}
