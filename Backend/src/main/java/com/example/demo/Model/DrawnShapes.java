package com.example.demo.Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.demo.shapes.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Component
public class DrawnShapes implements DrawnShapesI {
    private List<ShapeI> drawnShapes;
    private List<ShapeI> undoneShapes;
    private ObjectMapper mapper;

    public ShapeI checkCoordinate(Point click) {
       for(int i = drawnShapes.size()-1;i>=0;i--){
           ShapeI temp = drawnShapes.get(i);
           if(temp.range(click)){
               return temp;
           }
       }
       return null;
    }

    public DrawnShapes() {
        this.drawnShapes = new ArrayList<>();
        this.undoneShapes = new ArrayList<>();
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void undoShapes() {
        if (drawnShapes.size() != 0) {
            undoneShapes.add(drawnShapes.remove(drawnShapes.size() - 1));
        } else {
            System.out.println("Nothing to undo");
        }
    }

    public void addShape(ShapeI shape) {
        drawnShapes.add(shape);
    }

    public void redoShape() {
        if (undoneShapes.size() != 0) {
            drawnShapes.add(undoneShapes.remove(drawnShapes.size() - 1));
        } else {
            System.out.println("Nothing to redo");
        }
    }

    public JSONArray loadDrawnShapes(String path) {
        try {
            ShapeI[] shapes = mapper.readValue(new File(path), Shape[].class);
            System.out.println("File loaded successfully");
            undoneShapes.clear();
            drawnShapes = Arrays.asList(shapes);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return new JSONArray(drawnShapes);
    }

    public boolean saveDrawnShapes(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(path), drawnShapes);
        } catch (IOException exception) {
            return false;
        }
        return true;
    }

    public void move(){

    }
    public void copy(){

    }
    public void delete(){

    }

}