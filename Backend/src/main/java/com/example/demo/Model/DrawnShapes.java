package com.example.demo.Model;

import java.io.File;
import java.io.IOException;
import java.util.Stack;
import com.example.demo.shapes.ShapeI;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DrawnShapes implements DrawnShapesI {
    Stack<ShapeI> drawnShapes;
    Stack<ShapeI> undoneShapes;

    public DrawnShapes() {
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
        ObjectMapper mapper = new ObjectMapper();

        return this;
    }

    public boolean saveDrawnShapes(String name) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("./"+name), drawnShapes);
        }
        catch(IOException exception) {
            return false;
        }
        return true;
    }
}