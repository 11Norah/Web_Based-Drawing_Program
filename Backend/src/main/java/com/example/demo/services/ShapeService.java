package com.example.demo.services;

import com.example.demo.Model.DrawnShapes;
import com.example.demo.shapes.ShapeI;
import com.example.demo.Model.DrawnShapesI;

import org.springframework.stereotype.Component;

@Component
public class ShapeService {
    DrawnShapesI drawnShapes;

    ShapeService() {
        drawnShapes = new DrawnShapes();
    }

    public void add(ShapeI shape){
        drawnShapes.addShape(shape);
    }

    public void save(String name) {
        drawnShapes.saveDrawnShapes(name);
    }

}
