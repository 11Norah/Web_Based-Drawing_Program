package com.example.demo.Model;
import com.example.demo.shapes.ShapeI;

public interface DrawnShapesI {
    DrawnShapesI loadDrawnShapes(String name);
    void saveDrawnShapes(String name);
    void undoShapes();
    void addShape(ShapeI shape);
    void redoShape();
}