package com.example.demo.factory;

import com.example.demo.response.ResponseObjectI;
import com.example.demo.shapes.ShapeI;

import java.awt.*;

public interface ObjectFactoryI {
    public ShapeI getObject(String name,String color, Point first, Point second, Point third);


}
