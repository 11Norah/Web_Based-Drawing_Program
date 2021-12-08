package com.example.demo.Model;

import java.util.Stack;
import com.example.demo.shapes.ShapeI;
public class DrawnShapes implements DrawnShapesI {
    Stack<ShapeI> drawnShapes;
    Stack<ShapeI> undoneShapes;

    DrawnShapes() {
        this.drawnShapes = new Stack<>();
        this.undoneShapes = new Stack<>();
    }

    public void undoShapes() {
        if(!drawnShapes.empty()) {
            undoneShapes.push(drawnShapes.pop());
        }
        else {
            System.out.println("Nothing to undo");
        }
    }

    public void addShape(ShapeI shape) {
        drawnShapes.push(shape);
    }

    public void redoShape() {
        if(!undoneShapes.empty()) {
            drawnShapes.push(undoneShapes.pop());
        }
        else {
            System.out.println("Nothing to redo");
        }
    }

    public DrawnShapesI loadDrawnShapes(String name) {
        return this;
    }

    public void saveDrawnShapes(String name) {

    }
}