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

    public boolean checkCoordinate(Point click) {
        return drawnShapes.checkCoordinate(click);
    }


}
