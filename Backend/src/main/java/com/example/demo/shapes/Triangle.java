package com.example.demo.shapes;

import com.example.demo.services.LineServices;
import org.springframework.beans.factory.annotation.Autowired;

public class Triangle extends Shape implements ShapeI {
    private Point first, second, third;

    public Triangle() {}

    public Triangle(Point first, Point second, Point third, String color) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.color = color;
        this.name = "triangle";
    }


    LineServices lineServices = new LineServices();

    @Override
    public void afterMove(Point click) {
        double c = lineServices.getC(first, second);
        double m = lineServices.getM(first, second, c);
        double length = lineServices.getLength(first, second);
        second = lineServices.afterMove(click, m, first, second, length);
        c = lineServices.getC(first, third);
        m = lineServices.getM(first, third, c);
        length = lineServices.getLength(first, third);
        third = lineServices.afterMove(click, m, first, third, length);
        first = click;
    }

    public boolean range(com.example.demo.shapes.Point click) {
        return lineServices.checkPoint(first, second, click) || lineServices.checkPoint(first, third, click) || lineServices.checkPoint(second, third, click);
    }

    @Override
    public Point[] getPoints() {
        Point[] arr = new Point[3];
        arr[0] = first;
        arr[1] = second;
        arr[2] = third;
        return arr;
    }

    @Override
    public void resize(Point p1, Point p2) {
        this.second = p1;
        this.third = p2;
    }


}
