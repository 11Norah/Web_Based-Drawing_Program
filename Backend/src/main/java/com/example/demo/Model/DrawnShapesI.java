package com.example.demo.Model;
import com.example.demo.shapes.ShapeI;
import org.json.JSONArray;

public interface DrawnShapesI {
    JSONArray loadDrawnShapes(String path);
    boolean saveDrawnShapes(String path);
    void undoShapes();
    void addShape(ShapeI shape);
    void redoShape();
}