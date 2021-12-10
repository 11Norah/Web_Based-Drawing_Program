package com.example.demo.Model;
import com.example.demo.shapes.*;
import org.json.JSONArray;

public interface DrawnShapesI {
    JSONArray loadDrawnShapes(String path);
    boolean saveDrawnShapes(String path);
    void undoShapes();
    void addShape(Shape shape);
    void redoShape();
    int checkCoordinate(Point click);
    void move(int index,Point click);
    void copy(int index,Point click);
    void delete(int index);
}