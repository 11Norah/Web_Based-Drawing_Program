package com.example.demo.Model;
import com.example.demo.shapes.*;

public interface DrawnShapesI {
    void loadDrawnShapes(String path, String fileType);
    boolean saveDrawnShapes(String path, String fileType);
    void undoShapes();
    void addShape(Shape shape);
    void redoShape();
    int checkCoordinate(Point click);
    void move(int index,Point click);
    void copy(int index,Point click);
    void delete(int index);
}