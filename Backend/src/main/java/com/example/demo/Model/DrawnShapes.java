package com.example.demo.Model;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

import com.example.demo.shapes.ShapeI;

public class DrawnShapes implements DrawnShapesI {
    ArrayList<ShapeI> drawnShapes;
    Stack<ShapeI> undoneShapes;

    DrawnShapes() {
        this.drawnShapes = new ArrayList<ShapeI>();
        this.undoneShapes = new Stack<ShapeI>();
    }
    void undoShapes() throws EmptyStackException {
        if(!drawnShapes.isEmpty()) {
            int index = drawnShapes.size() - 1;
            undoneShapes.push(drawnShapes.get(index));
            drawnShapes.remove(index);
        }
        else {
            System.out.println("Nothing to undo");
        }
    }

    void addShape(ShapeI shape) {
        drawnShapes.add(shape);
    }

    void redoShape() {
        if(!undoneShapes.empty()) {
            drawnShapes.add(undoneShapes.pop());
        }
        else {
            System.out.println("Nothing to redo");
        }
    }

   /* DrawnShapesI loadDrawnShapes(String name) {

    }*/

    void saveDrawnShapes(String name) {

    }
}