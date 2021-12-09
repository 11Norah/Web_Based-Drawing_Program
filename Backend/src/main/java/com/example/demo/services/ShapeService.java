package com.example.demo.services;

import com.example.demo.Model.DrawnShapes;
import com.example.demo.shapes.Point;
import com.example.demo.shapes.ShapeI;
import com.example.demo.Model.DrawnShapesI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShapeService {


    @Autowired
    DrawnShapes drawnShapes;

    public void add(ShapeI shape) {
        drawnShapes.addShape(shape);
    }

    public void save(String path) {
        drawnShapes.saveDrawnShapes(path);
    }

    public int checkCoordinate(Point click) {
        return drawnShapes.checkCoordinate(click);
    }

    public void addResponse(String name, String color, Point first, Point second, Point third) {
        drawnShapes.addResponse(name, color, first, second, third);
    }

    public void move(int index, Point click) {
        drawnShapes.move(index, click);
    }

}
